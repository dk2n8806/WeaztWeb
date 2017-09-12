package com.common.adapter.stripe;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.stripe.model.Card;


/**
 * <h1>Card Adapter</h1>
 * 
 * <p>The class is an adapter to an Stripe.Card. Convert a Stripe.Card to
 * the an object usable in the app.</p>
 * <br>
 *  <p>Once debit/credit card is stored by 
 * Stripe DB, Stripe will provide a token that is used to access the card
 * for latter use.</p>
 * 
 * @author dk2n_
 *
 */
@Embeddable
public class CardAdapter implements Serializable {

	private static final long serialVersionUID = 1L;
	private String last4;
	private int expMonth;
	private int expYear;
	private String nameOnCard;
	private String currency = "usd";
	private String tokenId;
	private String brand;
	private String funding;
	
	/** :::
	 * 
	 * @param card Stripe.card
	 * @return
	 * ::: */
	public static  CardAdapter create(Card card) {
		try {
			if(card == null) 
				throw new IllegalArgumentException("Card is required");
			
			System.out.println("\n--------\n");
			System.out.println(card);
			
			CardAdapter result = new CardAdapter();
			result.nameOnCard = card.getName();
			result.tokenId 	= card.getId();
			result.currency = card.getCurrency();
			result.expMonth = card.getExpMonth();
			result.expYear 	= card.getExpYear();
			result.last4 	= card.getLast4();
			result.brand 	= card.getBrand();
			result.funding 	= card.getFunding();
			return result;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tlast4: " + last4 + "\n\texpMonth: " + expMonth + "\n\texpYear: " + expYear
				+ "\n\tnameOnCard: " + nameOnCard + "\n\tcurrency: " + currency + "\n\ttokenId: " + tokenId
				+ "\n\tbrand: " + brand + "\n\tfunding: " + funding + "\n}";
	}






	@Column(name="BRAND")
	public String getBrand() {		return brand;	}
	public void setBrand(String brand) {		this.brand = brand;	} 
	
	@Column(name="FUNDING")
	public String getFunding() {		return funding;	}
	public void setFunding(String funding) {	this.funding = funding;}
	
	@Column(name="NAME_ON_CARD", nullable=false)
	public String getNameOnCard() {		return nameOnCard;	}
	public void setNameOnCard(String nameOnCard) {		this.nameOnCard = nameOnCard;}
	
	
	
	@Column(name="CARD_TOKEN_ID", nullable=false)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	

	@Column(name="LAST_4", nullable=false)
	public String getLast4() {return last4;	}
	public void setLast4(String last4) {		this.last4 = last4;}

	@Column(name="CURRENCY")
	public String getCurrency() {		return currency;}
	public void setCurrency(String currency) {		this.currency = currency;}
	
	
	@Column(name="EXP_MONTH", nullable=false)
	public int getExpMonth() {		return expMonth;	}
	public void setExpMonth(int expMonth) {		this.expMonth = expMonth;}

	
	@Column(name="EXP_YEAR", nullable=false)
	public int getExpYear() {		return expYear;}
	public void setExpYear(int expYear) {		this.expYear = expYear;}

	
	
}
