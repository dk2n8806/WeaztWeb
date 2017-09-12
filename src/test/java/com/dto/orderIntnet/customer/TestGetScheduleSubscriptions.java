package com.dto.orderIntnet.customer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.product.ProductService;

public class TestGetScheduleSubscriptions extends BaseTest{

	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentSerivce;
	@Autowired private ProductService productService;
	
	
	private Product product;
	private DateInterval dateInterval;
	
	
	@Before
	public void init() {
		dateInterval = null;
		product = productService.getReference(new Long(32));
	}
	
	
	@Test
	public void test() {
		
		List<Subscription> subscriptions = 
				merchantOrderIntentSerivce.getScheduledSubscriptionsByProduct(product, OrderIntentStatus.REQUESTING, dateInterval, null);
		
		System.out.println(subscriptions.size());
	}
}
