package com.common.adapter.shippo;

public enum FedExBox {

	FEDEX_BOX_10kg("FedEx_Box_10kg");
	
	
	private String code;
	
	private FedExBox(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
