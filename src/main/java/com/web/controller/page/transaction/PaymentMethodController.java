package com.web.controller.page.transaction;


import java.util.ArrayList;
import java.util.List;

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
import com.admin.resource.WeaztAppResourceKeys;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeStatus;
import com.common.util.PageSearch;
import com.core.service.stripe.StripeCreatorService;
import com.core.service.stripe.StripeCustomerService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class PaymentMethodController implements IPaymentMethodAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private StripeCustomerService stripeCustomerService;
	@Autowired private StripeCreatorService stripeCreatorService;
	
	
	@RequestMapping(value=UriPageRequestMapping.Transaction.PAYMENT
					, method=RequestMethod.GET)
	public String showPaymentMethods(Model model
										, @ActiveUserPrincipal Account customer) {
		System.out.println();
		logger.info("Customer wanna show their payment method");
		StripeStatus status = StripeStatus.ACTIVE;
		List<StripeCustomer> stripes = new ArrayList<StripeCustomer>();
		long count = stripeCustomerService.countByAccount(customer, status);
		if(count > 0) {
			stripes = stripeCustomerService.getByAccount(customer, status, new PageSearch(0, count));
		}
		
		StripeCustomer defaultStripe = stripeCustomerService.getDefaultByAccount(customer);
		if(defaultStripe != null) {
			++count;
			stripes.add(0, defaultStripe);
		}
		
		model.addAttribute("stripeKey", WeaztAppResourceKeys.StripeResource.STRIPE_PUBLIC_KEY);
		model.addAttribute("count", count);
		model.addAttribute("stripes", stripes);
		
		return PageAdvice.Transaction.PAYMENT;
	}
	

	
	
		
	/****************************************************************
	 * Store a new payment by the customer
	 * 
	 * {@link IPaymentMethodAdvise#addNewPaymentMethod(Account, String, String, int, int, String, String)}
	 * 
	 */
	@RequestMapping(value=UriActionRequestMapping.Transaction.CREATE_NEW_PAYMENT
					, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	addNewMethod(@ActiveUserPrincipal Account customer
			, @RequestParam(name="_tokenId", defaultValue="") String tokenId) {
		System.out.println();
		logger.info("Customer request to add a new payment method");
		JsonResponse jsonResponse = new JsonResponse();
		
		try {
			StripeCustomer stripeCustomer = stripeCreatorService.createCustomerByCardToken(customer, tokenId);
			
			Assert.notNull(stripeCustomer, "Unable to create a new stripe_customer");
			
			jsonResponse.setState(true);
		}catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
	

	
	
	
		
		
	
	/****************************************************************
	 * delete an stripe_customer
	 * 
	 * {@link IPaymentMethodAdvise#deleteMethod(Account, Long)}
	 * 
	 */
	@RequestMapping(value=UriActionRequestMapping.Transaction.DELETE_A_PAYMENT
					, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	deleteMethod(@ActiveUserPrincipal Account customer
										, @RequestParam(name="_id") Long _stripeId) {
		System.out.println();
		logger.info("Customer request to remove a payment method " + _stripeId);
		JsonResponse jsonResponse = new JsonResponse();
		try {
			StripeCustomer stripe = stripeCustomerService.getByAccount(_stripeId, customer);
			
			Assert.notNull(stripe, "Unable to retrieve stripe_customer of id [" + _stripeId + "]");
			
			stripeCustomerService.markAsDeleted(stripe);
			jsonResponse.setState(true);
		} 
		catch(IllegalArgumentException e) {
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
	

	
	


/****************************************************************
 * Set an particular stripe_customer as 'DEFAULT'
 * 
 * {@link IPaymentMethodAdvise#setDefaultMethod(Account, Long)}
 * 
 */
	@RequestMapping(value=UriActionRequestMapping.Transaction.SET_DEFAULT_A_PAYMENT
					, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	setDefaultMethod(@ActiveUserPrincipal Account customer
											, @RequestParam(name="_id") Long _stripeId) {
		System.out.println();
		logger.info("Customer request to set a payment method as default: " + _stripeId);
		JsonResponse jsonResponse = new JsonResponse();
		try {
			stripeCustomerService.setDefaultByAccount(_stripeId, customer);
			jsonResponse.setState(true);	
		} 
		catch(IllegalArgumentException e) {
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
}
