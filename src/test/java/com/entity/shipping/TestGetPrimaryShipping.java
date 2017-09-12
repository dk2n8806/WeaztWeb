package com.entity.shipping;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.core.service.account.AccountService;
import com.core.service.account.SimpleShippingService;

public class TestGetPrimaryShipping extends BaseTest{

	@Autowired private SimpleShippingService shippingService;
	@Autowired private AccountService accountService;
	

	@Test
	public void test() {
		Account account = accountService.getReference(new Long(8));
		SimpleShipping shipping = shippingService.getPrimaryByAccount(account );
		assertTrue(shipping.isPrimary());
		System.out.println(shipping);
	}
}
