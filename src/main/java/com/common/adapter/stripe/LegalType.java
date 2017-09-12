package com.common.adapter.stripe;

public enum LegalType {

	INDIVIDUAL("individual")
	, COMPANY("company")
	;
	
	private String code;
	
	private LegalType(String code) { this.code = code;	}
	public String getCode() {
		return code;
	}
}
