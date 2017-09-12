package com.common.type;

public enum PayoutStatus {

	PENDING
	, COMPLETED
	;
	
	public static PayoutStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "pending": return PENDING;
			case "completed": return COMPLETED;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
	
}
