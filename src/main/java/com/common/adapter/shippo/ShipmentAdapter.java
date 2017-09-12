package com.common.adapter.shippo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.shippo.model.Rate;
import com.shippo.model.Shipment;



/**
 * Shipment adapter
 * @author dk
 *
 */
@Embeddable
public class ShipmentAdapter {

	
	private String tokenId;
	private List<RateAdapter> rateAdapters;
	
	
	
	/**
	 * 
	 * @param shipment
	 * @return
	 */
	public static ShipmentAdapter create(Shipment shipment) {
		// Validate Shippo_Shipment object
		if(shipment == null) {return null;}
		if(!shipment.getObjectState().equals("VALID")) {	return null;	}
		
		
		
		//System.out.println(shipment);
		List<Rate> rates = shipment.getRatesList();
		List<RateAdapter> rateAdapters = new ArrayList<RateAdapter>();
		for(Rate rate : rates) {
			rateAdapters.add(RateAdapter.create(rate));
		}
		ShipmentAdapter shipmentAdapter = new ShipmentAdapter();
		shipmentAdapter.setTokenId(shipment.getObjectId());
		shipmentAdapter.setRateAdapters(rateAdapters);;
		return shipmentAdapter;
	}
	
	
	@Transient
	public List<RateAdapter> getRateAdapters() {	return rateAdapters;}
	public void setRateAdapters(List<RateAdapter> rateAdapters) {	this.rateAdapters = rateAdapters;}
	
	
	
	@Column(name="SHIPPMENT_TOKEN_ID", nullable=false, updatable=false, unique=true)
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	
	
	
	
	
	public RateAdapter getLowestRate() {
		if(rateAdapters == null || rateAdapters.size() == 0) {
			return null;
		}
		if(rateAdapters.size() == 1) {
			return rateAdapters.get(0);
		}
		
		Collections.sort(rateAdapters, new Comparator<RateAdapter>() {
			@Override
			public int compare(RateAdapter r1, RateAdapter r2) {
				return (r1.getAmount() > r2.getAmount()) ? 1 : -1;
			}
		});
		
		return rateAdapters.get(0);
	}
}
