package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;

public class CardTokenAdapterAttribute extends AbstactStripeAdapterAttribute {

	private String tokenId;
	
	public CardTokenAdapterAttribute() {}
	public CardTokenAdapterAttribute(String tokenId) {
		this.tokenId = tokenId;
	}
	
	
	
	
	@Override
	public Map<String, Object> getMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("external_account", tokenId);
		return params;
	}
	
	
	
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

}
