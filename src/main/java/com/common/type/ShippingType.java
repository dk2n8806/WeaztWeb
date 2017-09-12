package com.common.type;

public enum ShippingType {

	AUTO
	, FREE
	, FLAT
	;
	
	
	public static ShippingType lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "auto": return AUTO;
			case "free" : return FREE;
			case "flat"  : return FLAT;
			default: return null;
			}
		} catch(NullPointerException e){
			return null;
		}
	}
	
}
