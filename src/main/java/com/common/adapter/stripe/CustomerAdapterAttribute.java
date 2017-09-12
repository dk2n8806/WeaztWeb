package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;


public class CustomerAdapterAttribute {

	private CardTokenAdapterAttribute cardToken;
	
	
	public CustomerAdapterAttribute() {}
	public CustomerAdapterAttribute(CardTokenAdapterAttribute cardToken) {
		super();
		this.cardToken = cardToken;
	}



	public Map<String, Object> getCardMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("object", "card");
		return params;
	}
	
	
	
	public Map<String, Object> getMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		if(cardToken != null) {
			params.put("source", cardToken.getTokenId());
		}
		return params;
	}
	
	
	public CardTokenAdapterAttribute getCardToken() {
		return cardToken;
	}
	public void setCardToken(CardTokenAdapterAttribute cardToken) {
		this.cardToken = cardToken;
	}
}
