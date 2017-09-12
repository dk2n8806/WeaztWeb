package com.common.entity.stripe;

public enum StripeStatus {

	DEFAULT
	, ACTIVE
	, DEACTIVE
	, DELETED
	//, PENDING
	;
	

	public static StripeStatus lookup(String code) {
		try {
			code = code.toLowerCase().trim();
			switch(code) {
			case "default":		return DEFAULT;
			case "active":		return ACTIVE;
			case "deactive":	return DEACTIVE;
			case "deleted":	return DELETED;
			default:				return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
