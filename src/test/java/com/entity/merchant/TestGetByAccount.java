package com.entity.merchant;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.type.Role;
import com.core.service.account.AccountService;
import com.core.service.merchant.MerchantService;

public class TestGetByAccount extends BaseTest{

	@Autowired private MerchantService merchantService;
	@Autowired private AccountService accountService;
	
	
	@Test
	public void test() {
		List<Account> accounts = accountService.getAccounts(Role.MERCHANT, null, null, null);
		System.out.println("accounts: " + accounts.size());
		
		for(Account account : accounts) 
			System.out.println("merchant: " + merchantService.getByAccount(account).getId());
	}
}
