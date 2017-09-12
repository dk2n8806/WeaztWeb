package com.entity;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.order.OrderBundle;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.process.order.IOrderProcessService;
import com.core.service.product.ProductService;

public class TestGenerateTransaction extends BaseTest{


	@Autowired private IOrderProcessService orderProcessService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderService;
	@Autowired private ProductService productService;
	private Product product;
	private DateInterval dateInterval;
	
	
	@Before
	public void init() {
		product = productService.findById(new Long(56));
		
		dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), -5);
	}
	
	
	
	
	@Test
	public void test() throws ProductException, OrderException, MerchantException {
		//List<Subscription> subscriptions = merchantOrderService.getScheduledSubscriptionsByProduct(product, OrderIntentStatus.REQUESTING	, dateInterval, null);
		//System.out.println(subscriptions.size());
		
		OrderBundle bundle = orderProcessService.generateOrder(product, dateInterval);		
		assertNotNull(bundle);
	}
	
}
