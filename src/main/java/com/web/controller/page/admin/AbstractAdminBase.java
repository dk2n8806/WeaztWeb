package com.web.controller.page.admin;


import com.common.entity.account.Account;
import com.common.exception.AccountCredentialException;
import com.common.type.AccountStatus;
import com.common.type.Role;

public class AbstractAdminBase {
	
	
	protected void isAdmin(Account account) throws AccountCredentialException {
		if(account.getStatus() != AccountStatus.ACTIVE
			 ||	account.getRole() != Role.ADMIN
			) {
			throw new AccountCredentialException("Invalid account credential");
		}
	}
	
	
	
}
