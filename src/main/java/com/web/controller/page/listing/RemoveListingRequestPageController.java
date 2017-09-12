package com.web.controller.page.listing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeleteProductToken;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.PageSearch;
import com.core.service.process.IDeleteProductProcessService;
import com.core.service.product.DeleteProductTokenService;
import com.core.service.product.ProductService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.core.service.subscription.SubscriptionService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;


/** 
 * The controller to show delete_request page a product by a client.
 * 
 * @author dk2n_
 *
 */
@Controller
public class RemoveListingRequestPageController 
	extends AbstractMerchantBaseController implements IDeleteListingAdvise{

	private static final Logger logger = LogManager.getLogger();

	
	@Autowired private DeleteProductTokenService tokenService;
	@Autowired private ProductService productService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private ProductTemplateDTOService templateService;
	@Autowired private IDeleteProductProcessService deleteProductService;
	
	
	
	/** :::
	 * Display delete_product request page
	 * 
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Listing.LISTING_DELETE_CONFIRM
					, method=RequestMethod.GET)
	public String showPage(Model model
									, @ActiveUserPrincipal Account account
									, @RequestParam(name="token", required=true) String _tokenId) throws MerchantException {
		System.out.println();
		logger.info("Display delete_listing page");
		
		final Merchant merchant = this.getAuthorizedMerchant(account);
		try {
			DeleteProductToken token = tokenService.getByMerchant(merchant, _tokenId);
			Assert.notNull(token, "Unable to retrieve token by the merchant");
			Assert.isTrue(token.getValidate(), "Unable to use token");
			
			ProductTemplateDTO template = templateService.getTemplate(token.getProduct());
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			List<Subscription> subscriptions = new ArrayList<>();
			
			long subscribedCount = subscriptionService.countByProduct(token.getProduct(), SubscriptionStatus.SUBSCRIBED, null);
			long subscribingCount = subscriptionService.countByProduct(token.getProduct(), SubscriptionStatus.SUBSCRIBING, null);

			if(subscribedCount > 0)  
				subscriptions.addAll(subscriptionService.getByProduct(token.getProduct()
						, SubscriptionStatus.SUBSCRIBED, null, new PageSearch(0,  (int)subscribedCount)));
			if(subscribingCount > 0)  
				subscriptions.addAll(subscriptionService.getByProduct(token.getProduct()
						, SubscriptionStatus.SUBSCRIBING, null, new PageSearch(0,  (int)subscribingCount)));
			
			
			params.put("Subscriptions", subscribedCount + subscribingCount);
			//params.put("Total", new SubscriptionRefundCalculator(subscriptions).getRefundAmount());
			
			//			
//			long customers = subscriptionService.countByProduct(token.getProduct(), null, null);
//			long subscribers = subscriptionService.countByProduct(token.getProduct(), SubscriptionStatus.SUBSCRIBED, null);
//			long orders = merchantOrderIntentService.countByProduct(token.getProduct(), OrderStatus.PENDING, null);
//			
//			
//			params.put("Subscriptions", customers);
//			params.put("Subscribers", subscribers);
//			params.put("Orders", orders);
//			
			model.addAttribute("template", template);
			model.addAttribute("token", token);
			model.addAttribute("params", params);
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw new GeneralResourceNotFoundException(e.getMessage());
		}
		
		return PageAdvice.Merchant.Listing.DELETE_LISTING;
	}

	
		
		
	/**
	 * Process to delete a product
	 * 
	 * - Remove the product out of the list
	 * - Mark token isUseable = false
	 * - Remove the order_intent out os the list
	 * - Send deleted_product request to server
	 * 
	 * {@link IDeleteListingAdvise#deleteProductRequest(Account, Long, String, Integer)}
	 */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.DELETE_CONFIRM
					, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse deleteProductRequest(@ActiveUserPrincipal Account account
											, @RequestParam Long _pid
											, @RequestParam(name="_token") String _token
											, @RequestParam(name="_quote", defaultValue="0") Integer _quote) 
													throws MerchantException 
	{
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				final Merchant merchant = this.getAuthorizedMerchant(account);
				DeleteProductToken token = tokenService.getByMerchant(merchant, _token);
				Assert.notNull(token, "Unable to retrieve token by the merchant");
				Assert.isTrue(token.getValidate(), "Unable to use token");

				// Lookup product on delete_product token
				Product product = productService.getByMerchant(token.getProduct().getId(), merchant);
				Assert.notNull(product, "Unable to retrieve product by the merchant");
				Assert.isTrue(!product.getStatus().equals(ProductStatus.DELETED), "Invalid product status");
				

				
				tokenService.markAsUsed(token);			// Deactive delete-token
				logger.info("deactive delete_token");
				
				deleteProductService.delete(product);		// Delete the product
				logger.info("successfuly delete the product");
				
				jsonResponse.setState(true);
			} catch(IllegalArgumentException e) {
				jsonResponse.setState(false);
				e.printStackTrace();
			} catch (ProductException | SubscriptionException | OrderException e) {
				jsonResponse.setState(false);
				e.printStackTrace();
			}
			return jsonResponse;
		}
	}
	

}
