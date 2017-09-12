package com.web.controller.page.subscription;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface ISubscriptionDetailAdvise {

	/** {@link SubscriptionDetailController#showSubscription(Model, Account, Long)} */
	String showSubscription(Model model, Account customer, Long subscriptionId);
	
	
	/** {@link SubscriptionDetailController#updateDeliverFrequency(Account, Long, int)} */
	JsonResponse updateDeliverFrequency(
			Account customer, Long subscriptionId, String newFrequency);

	/** {@link SubscriptionDetailController#generateUnsubscribeToken(Account, Long)}*/
	JsonResponse generateUnsubscribeToken(Account customer, Long subscriptionId);


	JsonResponse writeReview(Account account, Long subscriptionId, String content);
	
	
	/** {@link SubscriptionDetailController#toggleRating} */
	void toggleRating(Account account, Long subscriptionId, String whichRating);

}
