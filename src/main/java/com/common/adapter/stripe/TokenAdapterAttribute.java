package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;


/** 
 * <h1>Token Adapter Attribute</h1>
 * @author dk2n_
 *
 */
public class TokenAdapterAttribute extends AbstactStripeAdapterAttribute {

	private String DEFAULT_CURRENCY = "usd";
	
	private String number;
	private int expMonth;
	private int expYear;
	private int cvc;
	private String currency;
	private String name;
	
	public TokenAdapterAttribute() {}
	
	
	public TokenAdapterAttribute(String name, String number, int expMonth, int expYear, int cvc) {
		super();
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvc = cvc;
		this.name = name;
	}


	@Override
	public Map<String, Object> getMetadata() {
		Map<String, Object> cardParams = new HashMap<String, Object>();
		Map<String, Object> tokenParams = new HashMap<String, Object>();
		cardParams.put("number", number);
		cardParams.put("exp_month", expMonth);
		cardParams.put("exp_year",expYear);
		cardParams.put("cvc", cvc);
		cardParams.put("object", "card");
		cardParams.put("currency", DEFAULT_CURRENCY);
		cardParams.put("name", name);
		
		tokenParams.put("card", cardParams);
		return tokenParams;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
}
