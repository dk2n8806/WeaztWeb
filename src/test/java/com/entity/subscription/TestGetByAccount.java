package com.entity.subscription;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.entity.subscription.Subscription;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.service.product.ProductService;
import com.core.service.subscription.SubscriptionService;

public class TestGetByAccount  extends BaseTest {

	@Autowired private SubscriptionService subscriptionService;
	@Autowired private ProductService productService;
	
	private SubscriptionStatus status;
	private DateInterval dateInterval;
	private Pageable pageable;
	
	@Before
	public void init() {
		
	}
	
	
	@Test
	public void test() {
		List<Subscription> subscriptions = subscriptionService.getSubscriptions(status, dateInterval, pageable);
		for(Subscription s : subscriptions) 
		{
			System.out.println(s);
			System.out.println("\n");
			System.out.println(productService.findById(s.getProduct().getId()));
		}
	}
	
	
}
