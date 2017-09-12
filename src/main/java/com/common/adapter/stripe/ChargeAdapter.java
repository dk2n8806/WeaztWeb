package com.common.adapter.stripe;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.stripe.model.Charge;

/*********************************************************************************
 * 
 * @author dk
 *
 */
@Embeddable
public class ChargeAdapter {

	private int chargeAmount;
	private String tokenId;

	public static ChargeAdapter create(Charge charge) {
		try {
			if(charge == null) 
				throw new IllegalArgumentException("charge is required");
			ChargeAdapter result = new ChargeAdapter();
			result.setTokenId(charge.getId());		
			result.setChargeAmount(charge.getAmount());
			return result;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Column(name="CHARGE_TOKEN_ID", nullable=false, unique=true, updatable=false)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	
	
	@Column(name="CHARGE_AMOUNT")
	public int getChargeAmount() {		return chargeAmount;	}
	public void setChargeAmount(int fee) {	this.chargeAmount = fee;}
	
}
