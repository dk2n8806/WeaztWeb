package com.common.type;

public enum FeeStatus {

	PENDING
	, COMPLETED
	;
	
	public static FeeStatus lookup(String code) {
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
