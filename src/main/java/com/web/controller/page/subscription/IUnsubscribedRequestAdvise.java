package com.web.controller.page.subscription;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface IUnsubscribedRequestAdvise {

	/** Display subscription data so that the customer will be able
	 * to unsubscribe the product 
	 * {@link UnSubscribeRequestController#showUnsubscribePage(Model, Account, String)} */
	String showUnsubscribePage(Model model, Account customer, String unrequestToken);

	
	
	/** Process cancelation of a product by an account
	 * {@link UnSubscribeRequestController#unsubscribeRequest(Account, Long, String, Integer)}*/
	JsonResponse unsubscribeRequest(Account customer
					, Long subscriptionId
					, String token
					, Integer quote);	
}
