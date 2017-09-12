package com.common.adapter.stripe;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;


/**
 * <h1>CustomerAdapter</h1>
 * 
 * <p>An adapter of Stripe.Customer.</p>
 * <br>
 * <p>The class provide an only to communicate to Stripe.customer</p>
 * 
 * @author dk2n_
 *
 */
@Embeddable
public class CustomerAdapter implements Serializable {


	private static final long serialVersionUID = 1L;
	private String tokenId;
	private CardAdapter cardAdapter;
	
	
	public CustomerAdapter() {}
	public CustomerAdapter(String tokenId) {
		this.tokenId = tokenId;
	}
	
	

	public static CustomerAdapter create(Customer customer) {
		if(customer == null) {
			return null;
		}
		CustomerAdapter result = new CustomerAdapter();
		result.setTokenId(customer.getId());
		ExternalAccountCollection collection = customer.getSources();
		for(ExternalAccount card : collection.getData()) {
			result.setCardAdapter(CardAdapter.create((Card)card));
		}		
		return result;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\ttokenId: " + tokenId + "\n\tcardAdapter: " + cardAdapter + "\n}";
	}
	
	
	
	
	@Embedded
	public CardAdapter getCardAdapter() {		return cardAdapter;	}
	public void setCardAdapter(CardAdapter cardAdapter) {	this.cardAdapter = cardAdapter;}
	
	
	@Column(name="CUSTOMER_TOKEN_ID", nullable=false, updatable=false, unique=true)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	
	
	
}
