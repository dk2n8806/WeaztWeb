package com.common.type;

public enum SubscriptionStatus {

	SUBSCRIBED
	, SUBSCRIBING
	, UNSUBSCRIBED
	;
	
	public static SubscriptionStatus lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "subscribed":  		return SUBSCRIBED;
			case "subscribing": 		return SUBSCRIBING;
			case "unsubscribed": 	return UNSUBSCRIBED;
			default: return null;
			}
		} catch(NullPointerException e){
			return null;
		}
	}
	
}
