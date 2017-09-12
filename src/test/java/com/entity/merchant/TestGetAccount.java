package com.entity.merchant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.core.service.merchant.MerchantService;

public class TestGetAccount extends BaseTest{
 
	@Autowired private MerchantService merchantService;
	private Merchant merchant;
	
	@Before
	public void init() {
		merchant = merchantService.getReference(new Long(32));
	}
	
	@Test
	public void test() {
		Account account = merchantService.getAccount(merchant);
		assertNotNull(account);
		
		System.out.println(account.getEmail());
	}
}
