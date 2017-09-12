package com.web.controller.page.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.order.OrderIntent;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.util.PageSearch;
import com.core.service.merchant.MerchantService;
import com.core.service.order.OrderIntentService;
import com.core.service.product.ProductService;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.dto.SubscriptionTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.exception.global.GeneralResourceNotFoundException;


@Controller
public class CheckoutConfirmPageController implements ICheckoutConfirmationAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private ProductService productService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private SubscriptionTemplateDTOService templateService;
	
/**************************************
 * Request to show checkout confirm page
 * 
 * {@link ICheckoutConfirmationAdvise#showConfirmationPage(Model, Account, Long)}
 */
	@RequestMapping(value=UriPageRequestMapping.User.Checkout.CHECKOUT_CONFIRM
					, method=RequestMethod.GET)
	@Override
	public String showConfirmationPage(Model model
								, @ActiveUserPrincipal Account account
								, @RequestParam(required=false) Long _sid) {
		System.out.println();
		logger.info("Request to show checkout-confirm page");
		
		try{
			Assert.notNull(_sid);
			
			Subscription subscription = subscriptionService.getByAccount(_sid, account);
			Assert.notNull(subscription, "Unable to retrieve subscription of the id [" + _sid + "]");
			
			SubscriptionTemplateDTO template = templateService.getTemplate(subscription);
			OrderIntent orderIntent = orderIntentService.getBySubscription(subscription
																						, OrderIntentStatus.REQUESTING, null, new PageSearch(0, 1))
																				.get(0);
			
			model.addAttribute("merchant", productService.getMerchant(subscription.getProduct()));
			model.addAttribute("template", template);
			model.addAttribute("subscriptionNumber", subscription.getCreatedOn());
			model.addAttribute("order", orderIntent);
			
			return PageAdvice.Checkout.CHECKOUT_CONFIRM;
		} catch(NullPointerException | IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw new GeneralResourceNotFoundException(e.getMessage());
		}
	}
	
	

}
