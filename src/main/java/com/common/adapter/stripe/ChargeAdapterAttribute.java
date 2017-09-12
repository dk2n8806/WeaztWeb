package com.common.adapter.stripe;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.common.adapter.stripe.util.StripeCurrencyAdapter;

public class ChargeAdapterAttribute {

	private static final Logger logger = LogManager.getLogger();
	
	private final String DESCRIPTION = "Purchased Mingofy's subscription";
	private final int SMALLEST_CHARGE_AMOUNT = 50;
	
	private int chargeAmount;
	private CustomerAdapter customer;
	
	
	public ChargeAdapterAttribute(CustomerAdapter customer, int chargeAmount) {
		this.customer = customer;
		this.chargeAmount = chargeAmount;
	}
	
	
	
	
	
	
/*****************************************************************************
 * @return charge data
 */
	public Map<String, Object> getChargeMetadata() {
		if(this.customer == null
				|| this.customer.getTokenId() == null) {
			logger.error("require stripe_customer");
			throw new IllegalArgumentException("Require stripe_customer");
		}
		
		if(chargeAmount < SMALLEST_CHARGE_AMOUNT) {
			throw new IllegalArgumentException("Charge totalCharge can't be less than [" 
												+ SMALLEST_CHARGE_AMOUNT + "]");
		}
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", chargeAmount);
		params.put("customer", this.customer.getTokenId());
		params.put("description", DESCRIPTION);
		params.put("currency", StripeCurrencyAdapter.DEFAULT_CURRENTCY);
		params.put("capture", true);
		return params;
	}
	
	
	public int getChargeAmount() {		return chargeAmount;	}
	public void setChargeAmount(int chargeAmount) {		this.chargeAmount = chargeAmount;	}

	
	
	public CustomerAdapter getCustomer() {		return customer;}
	public void setCustomer(CustomerAdapter custommer) {	this.customer = custommer;}
	
	
	
}
