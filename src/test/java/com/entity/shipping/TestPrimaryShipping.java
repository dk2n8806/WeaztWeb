package com.entity.shipping;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.SimpleShipping;
import com.core.service.account.SimpleShippingService;

public class TestPrimaryShipping extends BaseTest{

	@Autowired private SimpleShippingService shippingService;
	
	
	@Test
	public void test() {
		SimpleShipping shipping = shippingService.findById(new Long(41));
		shippingService.setPrimary(shipping);;
	}
}
