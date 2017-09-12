package com.entity.account;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.core.service.account.auth.AccountAuthenticationService;

public class TestCreateAccount extends BaseTest{

	@Autowired private AccountAuthenticationService accountAuthenticationService;
	
	@Test
	public void test() 
	{
		Account account = new Account("derek", "dk1@gmail.com");
		String password = "password";
		accountAuthenticationService.save(account, password);
	}
	
	
	public static Account getAccounts() {
		String username = "merchant1";
		String email = "merchant1@gmail.com";
		return new Account(username,email);		
	
	}
	
	
}
