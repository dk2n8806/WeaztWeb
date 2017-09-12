package com.common.adapter.shippo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.shippo.model.Transaction;

@Embeddable
public class LabelAdapter {


	private String tokenId;
	private String status;
	private String rateId;
	private String trackingNumber;
	private String trackingUrl;
	private String labelUrl;
	private int shippingCost;
	
	
	
	public static LabelAdapter create(Transaction transaction, int shippingCost) {
		if(transaction == null) {
			return null;
		}
		LabelAdapter adapter = new LabelAdapter();
		adapter.tokenId 		= transaction.getObjectId();
		adapter.status 			= String.valueOf(transaction.getObjectStatus());
		adapter.rateId 			= String.valueOf(transaction.getRate());
		adapter.trackingNumber 	= String.valueOf(transaction.getTrackingNumber());
		adapter.trackingUrl 	= String.valueOf(transaction.getTrackingUrlProvider());
		adapter.labelUrl 		= String.valueOf(transaction.getLabelUrl());
		adapter.shippingCost	= shippingCost;
		return adapter;
	}
	
	
	@Column(name="SHIPPING_COST")
	public int getShippingCost() {		return shippingCost;	}
	public void setShippingCost(int shippingCost) {	this.shippingCost = shippingCost;}
	
	
	@Column(name="TRANSACTION_TOKEN_ID", nullable=false, updatable=false, unique=true)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}




	@Column(name="STATUS", nullable=false, updatable=false)
	public String getStatus() {		return status;}
	public void setStatus(String status) {		this.status = status;}

	
	
	@Column(name="RATE_ID", nullable=false, updatable=false)
	public String getRateId() {		return rateId;}
	public void setRateId(String rateId) {		this.rateId = rateId;}

	
	@Lob
	@Column(name="TRACKING_NUMBER", nullable=false, updatable=false)
	public String getTrackingNumber() {		return trackingNumber;}
	public void setTrackingNumber(String trackingNumber) {		this.trackingNumber = trackingNumber;}

	
	@Lob
	@Column(name="TRACKING_URL", nullable=false, updatable=false)
	public String getTrackingUrl() {		return trackingUrl;}
	public void setTrackingUrl(String trackingUrl) {		this.trackingUrl = trackingUrl;}

	
	@Lob
	@Column(name="LABLE_URL", nullable=false, updatable=false)
	public String getLabelUrl() {		return labelUrl;}
	public void setLabelUrl(String labelUrl) {		this.labelUrl = labelUrl;}


	@Override
	public String toString() {
		return "LabelAdapter [\ntokenId=" + tokenId + "\n, status=" + status
				+ "\n, rateId=" + rateId + "\n, trackingNumber=" + trackingNumber
				+ "\n, trackingUrl=" + trackingUrl + "\n, labelUrl=" + labelUrl
				+ "]";
	}



	

}
