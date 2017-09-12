package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;

import com.common.adapter.stripe.util.StripeCurrencyAdapter;

public class TransferAdapterAttribute  {

	private static final int SMALLESS_TRANSFER_AMOUNT = 50;
	private static final String DESCRIPTION = "Mingofy's Sale";
	private AccountAdapter account;
	private int transferAmount;
	

	
	
	public TransferAdapterAttribute(AccountAdapter account,
			int transferAmount) {
		super();
		this.account = account;
		this.transferAmount = transferAmount;
	}




	public Map<String, Object> getTransferMetadata() {
		
		if(transferAmount < SMALLESS_TRANSFER_AMOUNT) {
			throw new IllegalArgumentException("Transfer totalCharge can't be less than " 
												+ SMALLESS_TRANSFER_AMOUNT);
		}
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", transferAmount);
		params.put("destination", this.account.getTokenId());
		params.put("description", DESCRIPTION);
		params.put("currency", StripeCurrencyAdapter.DEFAULT_CURRENTCY);
		return params;
	}
	

	
	
	
}
