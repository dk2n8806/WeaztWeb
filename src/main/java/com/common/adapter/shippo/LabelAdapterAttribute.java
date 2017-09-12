package com.common.adapter.shippo;

import java.util.HashMap;
import java.util.Map;

public class LabelAdapterAttribute {

	private String rate;
	
	public LabelAdapterAttribute(String rate) {
		this.rate = rate;
	}
	
	
	
	public Map<String, Object> getTransactionData() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rate", this.rate);
		params.put("async", false);
		return params;
	}
}
