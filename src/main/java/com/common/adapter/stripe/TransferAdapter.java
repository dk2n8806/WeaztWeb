package com.common.adapter.stripe;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.stripe.model.Transfer;


/******************************************************
 * 
 * @author dk
 *
 */
@Embeddable
public class TransferAdapter {

	private int amount;	
	private String tokenId;
	
	@Column(name="TRANSFER_TOKEN_ID")
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	

	
	public static TransferAdapter convert(Transfer transfer) {
		if(transfer == null) {
			return null;
		}
		//StripeCurrencyAdapter currencyAdapter = new StripeCurrencyAdapter();
		TransferAdapter result = new TransferAdapter();
		
		result.tokenId = transfer.getId();
		result.amount = transfer.getAmount(); 
		//setAmount(currencyAdapter.convertToDecimal(transfer.getAmount()));
		return result;
	}
	
	public int getAmount() {		return amount;	}
	public void setAmount(int amount) {	this.amount = amount;}
}
