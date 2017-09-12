package com.web.controller.page.transaction;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.web.response.JsonResponse;

public interface IPaymentMethodAdvise {

	
	/** Create & persist a new payment method {@link StripeCustomer}
	 * by the customer
	 *  
	 * {@link PaymentMethodController#addNewMethod(Account, String)} */
	JsonResponse addNewMethod(Account customer, String tokenId);
	
	
	/** Delete a payment_method {@link StripeCustomer} by the customer
	 *  
	 * {@link PaymentMethodController#deleteMethod(Account, Long)} */
	JsonResponse deleteMethod(Account customer, Long _paymentId);
	
	
	/** Set a payment_method {@link StripeCustomer} by the customer 
	 * 
	 * {@link PaymentMethodController#setDefaultMethod(Account, Long)} */
	JsonResponse setDefaultMethod(Account customer, Long _paymentId);
}
