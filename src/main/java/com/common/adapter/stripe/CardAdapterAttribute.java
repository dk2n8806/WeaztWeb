package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;

public class CardAdapterAttribute extends AbstactStripeAdapterAttribute {

	public CardAdapterAttribute() {}

	public Map<String, Object> getMetadata() {
		Map<String, Object> cardParams = new HashMap<String, Object>();
		cardParams.put("object", "card");
		return cardParams;		
	}

}
