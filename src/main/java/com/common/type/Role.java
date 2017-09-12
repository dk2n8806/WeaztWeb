package com.common.type;

import javax.persistence.NoResultException;

public enum Role {

	CUSTOMER
	, MERCHANT
	, ADMIN
	;
	
	public static Role lookup(String role) {
		try {
			switch (role.trim().toLowerCase()) {
			case "customer": 	return Role.CUSTOMER;
			case "merchant": 	return Role.MERCHANT;
			case "admin":		 	return Role.ADMIN;
			default: return null;
			}
		} catch(NoResultException e) {
			return null;
		}
	}
}
