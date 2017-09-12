package com.web.controller.page.transaction;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeAccount;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;

public interface IPayoutMethodAdvise {

	/** Display a list of payout_method {@link StripeAccount} 
	 * 
	 * {@link PayoutMethodController#showPayoutMethods(Model, Account)} */
	String showPayoutMethods(Model model, Account customer);
	
	
	/** Create & save a new payout_method {@link StripeAccount}
	 *  
	 * {@link PayoutMethodController#addNewMethod(Account, String, HttpServletRequest)} */
	JsonResponse addNewMethod(Account customer, String tokenId, HttpServletRequest request)
						throws MerchantException;
	
	
	/** Delete a payout_method {@link StripeAccount}  
	 * 
	 * {@link PayoutMethodController#deleteMethod(Account, Long)} */
	JsonResponse deleteMethod(Account customer, Long _paymentId)
			throws MerchantException;
	
	
	
	/** Set a payout_method as default
	 *  
	 * {@link PayoutMethodController#setDefaultMethod(Account, Long)} */
	JsonResponse setDefaultMethod(Account customer, Long _paymentId)
			throws MerchantException;
	
}
