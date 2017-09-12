package com.common.type;

public enum PaymentStatus {

	PENDING
	, VOIDED
	, COMPLETED
	;
	
	public static PaymentStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "pending": return PENDING;
			case "voided": return VOIDED;
			case "completed": return COMPLETED;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
