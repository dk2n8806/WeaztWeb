package com.common.type;


public enum AccountStatus {

	ACTIVE
	, DEACTIVE
	;
	
	public static AccountStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "active": 		return AccountStatus.ACTIVE;
			case "deactive" : return AccountStatus.DEACTIVE;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
