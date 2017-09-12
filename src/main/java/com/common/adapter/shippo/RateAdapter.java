package com.common.adapter.shippo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.shippo.model.Rate;

@Embeddable
public class RateAdapter {

	private String tokenId;
	private int amount;
	private String serviceLevel;
	private String provider;
	private String providerImage;
	
	
	

	public static RateAdapter create(Rate rate) {
		if(rate == null) {
			return null;
		}
		//System.out.println(rate);
		RateAdapter rateAdapter = new RateAdapter();
		rateAdapter.tokenId = rate.getObjectId();
		rateAdapter.amount = (int) (100 * Double.valueOf(String.valueOf(rate.getAmount())));
		rateAdapter.serviceLevel = String.valueOf(rate.getServicelevelName());
		rateAdapter.provider = String.valueOf(rate.getProvider());
		rateAdapter.providerImage = String.valueOf(rate.getProviderImage75());
		return rateAdapter;
	}
	
	
	
	
	public String getServiceLevel() {		return serviceLevel;}
	public void setServiceLevel(String serviceLevel) {		this.serviceLevel = serviceLevel;}
	
	@Column(name="AMOUNT")
	public int getAmount() {return amount;}
	public void setAmount(int amount) {	this.amount = amount;}
	
	
	@Column(name="RATE_TOKEN_ID", nullable=false, updatable=false, unique=true)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}

	
	@Column(name="PROVIDER")
	public String getProvider() {return provider;}
	public void setProvider(String provider) {this.provider = provider;}
	
	
	@Lob
	@Column(name="PROVIDER_IMAGE")
	public String getProviderImage() {return providerImage;	}
	public void setProviderImage(String providerImage) {	this.providerImage = providerImage;}




	@Override
	public String toString() {
		return "RateAdapter [\n\ttokenId=" + tokenId + ",\n\tamount=" + amount
				+ ",\n\tserviceLevel=" + serviceLevel + ",\n\tprovider=" + provider
				+ ",\n\tproviderImage=" + providerImage + "\n]";
	}



	
	
	
	
}
