package com.common.adapter.stripe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.hibernate.annotations.Type;

import com.stripe.model.Account;
import com.stripe.model.Card;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;


/**
 * <h1>Account Adapter</h1>
 * 
 * 
 * 
 * @author dk2n_
 *
 */
@Embeddable
public class AccountAdapter {

	
	private boolean isTransferEnabled;
	private String tokenId;
	private CardAdapter cardAdapter;
	

	public AccountAdapter() {}
	public AccountAdapter(String tokenId) {		this.tokenId = tokenId; }
	
	
	public static AccountAdapter create(Account account) {
		if(account == null) {		return null;}
		
		AccountAdapter result = new AccountAdapter();
		result.setTokenId(account.getId());
		result.setTransferEnabled(account.getTransfersEnabled());	
		
		ExternalAccountCollection collection = account.getExternalAccounts();
		for(ExternalAccount card : collection.getData()) {
			result.setCardAdapter(CardAdapter.create((Card)card));
		}
		return result;
	}

	


	@Embedded
	public CardAdapter getCardAdapter() {		return cardAdapter;	}
	public void setCardAdapter(CardAdapter cardAdapter) {	this.cardAdapter = cardAdapter;}
	


	@Type(type="yes_no")
	@Column(name="IS_TRANSFER_ENABLED", nullable=false)
	public boolean isTransferEnabled() {	return isTransferEnabled;}
	public void setTransferEnabled(boolean isTransferEnabled) {		this.isTransferEnabled = isTransferEnabled;}




	@Column(name="ACCOUNT_TOKEN_ID", nullable=false, unique=true, updatable=false)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	


	
	




}
