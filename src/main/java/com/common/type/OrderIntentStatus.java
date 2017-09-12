package com.common.type;

public enum OrderIntentStatus {

	COMPLETED
	, REQUESTING
	, CANCELED
	;
	
	public static OrderIntentStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "completed": return COMPLETED;
			case "requesting": return REQUESTING;
			case "canceled": return CANCELED;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
