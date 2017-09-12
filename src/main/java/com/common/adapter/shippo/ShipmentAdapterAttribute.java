package com.common.adapter.shippo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShipmentAdapterAttribute implements Serializable {

	private static final long serialVersionUID = 1L;
	private String addressSenderTokenId;
	private String addressReceiverTokenId;
	private String parcelTokenId;
	private ShippoPurpose purpose;
	private ShipmentType type;

	public ShipmentAdapterAttribute(String addressSenderTokenId,
			String addressReceiverTokenId, String parcelTokenId) {
		super();
		this.addressSenderTokenId = addressSenderTokenId;
		this.addressReceiverTokenId = addressReceiverTokenId;
		this.parcelTokenId = parcelTokenId;
	}




	public ShipmentAdapterAttribute(String addressSenderTokenId,
			String addressReceiverTokenId, String parcelTokenId,
			ShippoPurpose purpose, ShipmentType type) {
		super();
		this.addressSenderTokenId = addressSenderTokenId;
		this.addressReceiverTokenId = addressReceiverTokenId;
		this.parcelTokenId = parcelTokenId;
		this.purpose = purpose;
		this.type = type;
	}







	public Map<String, Object> getMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		//System.out.println("form: " + addressSenderTokenId);
		//System.out.println("to: " + addressReceiverTokenId);
		//System.out.println("parcel: " + addressSenderTokenId);
		params.put("address_to", this.addressReceiverTokenId);
		params.put("address_from", this.addressSenderTokenId);
		params.put("parcel", this.parcelTokenId);
		if(purpose == null) {
			params.put("object_purpose", ShippoPurpose.PURCHASE);
		} else {
			params.put("object_purpose", purpose);
		}
		
		if(type == null) {
			params.put("submission_type", ShipmentType.DROPOFF);
		} else {
			params.put("submission_type", type);
		}
		params.put("async", false);

		return params;
	}
	
	
	public ShipmentType getType() {
		return type;
	}
	public void setType(ShipmentType type) {
		this.type = type;
	}
	
	public ShippoPurpose getPurpose() {
		return purpose;
	}
	public void setPurpose(ShippoPurpose purpose) {
		this.purpose = purpose;
	}
	
	
}
