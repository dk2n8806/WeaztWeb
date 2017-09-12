package com.web.controller.page.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.order.CustomerOrderIntentTemplateDTO;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.common.type.OrderIntentStatus;
import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderIntentService;
import com.core.service.order.dto.OrderIntentTemplateDTOService;
import com.core.service.process.order.IOrderProcessService;
import com.core.service.process.subscription.ICancelSubscriptionProcessService;
import com.core.service.product.ProductService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;


@Controller
public class OrderIntentDetailPageController extends AbstractOrderHelper
implements IOrderIntentDetailAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	/** Display data services */
	@Autowired private OrderIntentTemplateDTOService orderTemplateService;
	@Autowired private ProductTemplateDTOService productTemplateService;
	
	
	/** Lookup data services */
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentService;
	@Autowired private ProductService productSerivce;
	

	/** Processor service */
	@Autowired private IOrderProcessService orderProcessService;
	
	@Autowired private ICancelSubscriptionProcessService cancelSubscriptionService;
	
	private final OrderIntentStatus ORDER_PENDING = OrderIntentStatus.REQUESTING;
	
	
	
		
	/**
	 * <p>Display a list of customers publicable info for the merchant </p>
	 * 
	 * {@link IOrderIntentDetailAdvise#showCustomerDetailOnProduct(Model, Account, Long, String)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Order.ORDER_INTENT_DETAIL
					, method=RequestMethod.GET)
	@Override
	public String showCustomerDetailOnProduct(Model model
						, @ActiveUserPrincipal Account customer
						, @PathVariable Long productId
						, @RequestParam(defaultValue="today") String _date) throws MerchantException {
	
		System.out.println();
		logger.info("Process an orders by a merchant on a product");	
		final Merchant merchant = this.getAuthorizedMerchant(customer);
	
		List<CustomerOrderIntentTemplateDTO> customers = new ArrayList<CustomerOrderIntentTemplateDTO>();
		Product product = null;
		ProductTemplateDTO template = null;
		DateInterval dateInterval = this.getDateInterval(_date);
		Pageable pageable = null;
		int revenue = 0;
		
		try {
			product = productSerivce.getByMerchant(productId, merchant);
			Assert.isTrue(!product.getStatus().equals(ProductStatus.DELETED), "Invalid product status");
			template = productTemplateService.getTemplate(product);
				
			
			customers = orderTemplateService.getByProduct(product, ORDER_PENDING, dateInterval, pageable);
			
			for(CustomerOrderIntentTemplateDTO c : customers) {
				revenue += c.getSubscriptionCost();
			}
			
		} catch(IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
			throw new GeneralResourceNotFoundException();
		}
		
		
		model.addAttribute("dateInterval", dateInterval);
		model.addAttribute("product", template);
		model.addAttribute("customers", customers);
		model.addAttribute("totalCount", customers.size());
		model.addAttribute("totalRevenue", revenue);
		model.addAttribute("_date", _date);
		return PageAdvice.Merchant.Order.ORDER_INTENT_CUSTOMER_DETAIL;
		
	}
		

	
		
		
	/** :::
	 * <p>Process order_intents and generate a order_bundle</p>
	 * 
	 * {@link IOrderIntentDetailAdvise#processOrderIntents(Account, Long, String)}
	 * 
	 */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Order.ACCEPT_CUSTOMER_ORDER
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse processOrderIntents(@ActiveUserPrincipal Account customer
											, @RequestParam(name="_pid") Long _productId
											, @RequestParam(defaultValue="today") String _date) 
	{
		System.out.println();
		logger.info("Process to generate order by the merchant");
		
		synchronized (this) {
			final Merchant merchant = customer.getMerchant();
			JsonResponse jsonResponse = new JsonResponse();

			try {
				Product product = productSerivce.getByMerchant(_productId, merchant);
				
				Assert.notNull(product, "Unable to retrieve product data by merchant");
				Assert.isTrue(!product.getStatus().equals(ProductStatus.DELETED)
							, "Invalid product stage - the product is already mark as 'DELETED'");
				
				DateInterval dateInterval = this.getDateInterval(_date);
				OrderBundle order = orderProcessService.generateOrder(product, dateInterval);
				
				jsonResponse.setState(true);
				jsonResponse.setResult(UriPageRequestMapping.Merchant.Order.ORDER_TRANSACTIONS + "/" +  order.getId());
			} 
			catch(IllegalArgumentException | ProductException | OrderException | MerchantException e) {
				jsonResponse.setState(false);
				e.printStackTrace();
			} 
			return jsonResponse;
		}
	}
	
	
	
	
	/** :::
	 * <p>Handle request to cancel a subscription by a merchant.</p>
	 * 
	 * {@link IOrderIntentDetailAdvise#dequeueCustomerFromQueue(Account, Long)}
	 */
	@RequestMapping(name=UriActionRequestMapping.Merchant.Order.DEQUEUE_CUSTOMER_ORDER
			, method=RequestMethod.POST)	
	@ResponseBody
	@Override
	public JsonResponse dequeueCustomerFromQueue(@ActiveUserPrincipal Account customer
							, @RequestParam Long _oid
							, @RequestParam(defaultValue="") String _quote) throws MerchantException {
		System.out.println();
		logger.info("Account request to dequeue a product");
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			final Merchant merchant = getAuthorizedMerchant(customer);	
			
			OrderIntent orderIntent = merchantOrderIntentService.getByMerchant(_oid, merchant);
			Assert.notNull(orderIntent, "Unable to retrieve order_intent data of [" + _oid + "]");

			Subscription subscription = orderIntentService.getSubscription(orderIntent);
			Assert.notNull(subscription, "Unable to retrieve subscription data");
			Assert.isTrue(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)
							, "Invalid subscription status - already marked as 'UNSUBSCRIBED'");
			
			try {
				cancelSubscriptionService.cancel(subscription);
				jsonResponse.setState(true);
			} catch (SubscriptionException e) {
				e.printStackTrace();
				jsonResponse.setState(false);
			}
			return jsonResponse;
		}
	}
}
