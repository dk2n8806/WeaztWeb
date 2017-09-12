package com.web.controller.page.order;

import java.util.ArrayList; 
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.dto.order.decorator.OrderTemplateAggregate;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.exception.MerchantException;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.dto.OrderIntentTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


/**
 * OrderIntentController
 *
 * This controller is displayed when the merchant request to lookup the orders.
 * The merchant will be able to capture his recurrent and new orders as up-to-date.
 *
 */
@Controller
public class OrderIntentPageController extends AbstractOrderHelper
implements IOrderIntentAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private OrderIntentTemplateDTOService orderTemplateService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentService;
	


	
	

	/** :::
	 * <p>Display a list of order_intent by the merchant<p>
	 * 
	 * {@link IOrderIntentAdvise#showGroupOrderOnProduct}
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Order.ORDER_INTENT
					, method=RequestMethod.GET)
	public String showGroupOrderOnProducts(Model model
								, @ActiveUserPrincipal Account customer
								, @RequestParam(name="_date", defaultValue="today") String _date
								) {
		logger.info("Request to show orderEntity on merchant");
		try {
			final Merchant merchant = this.getAuthorizedMerchant(customer);
			
			List<OrderIntentTemplateDTO> orderList = new ArrayList<OrderIntentTemplateDTO>();
			final OrderIntentStatus status = OrderIntentStatus.REQUESTING;
			long totalOrder = 0;
			Pageable pageable = null;
			DateInterval dateInterval = this.getDateInterval(_date);
			
			
			totalOrder = merchantOrderIntentService.countByMerchant(merchant, status, dateInterval);
			orderList = orderTemplateService.getByMerchant(merchant, status, dateInterval, pageable);
			
			
			model.addAttribute("_date", _date);
			model.addAttribute("newOrders", new OrderTemplateAggregate().counTotalOrder(orderList));
			model.addAttribute("totalCount", totalOrder);
		}
		catch(MerchantException e) {
			e.printStackTrace();
		}
		return PageAdvice.Merchant.Order.ORDER_INTENT;
	}





	
}
