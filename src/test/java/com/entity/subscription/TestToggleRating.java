package com.entity.subscription;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.subscription.Subscription;
import com.core.service.subscription.SubscriptionService;

public class TestToggleRating extends BaseTest{

	@Autowired private SubscriptionService subscriptionService;
	
	@Test
	public void test() {
		Subscription subscription = subscriptionService.findById(new Long(2132061));
		subscriptionService.toggleFavorite(subscription.getId(), subscription.getAccount());
		
	}
}
