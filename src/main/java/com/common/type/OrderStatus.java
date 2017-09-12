package com.common.type;

public enum OrderStatus {

	PENDING
	, COMPLETED
	;

	public static OrderStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "completed": return COMPLETED;
			case "pending": return PENDING;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
