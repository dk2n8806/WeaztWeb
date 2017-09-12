package com.common.entity.order;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.adapter.shippo.RateAdapter;
import com.common.entity.AbstractPersistenceObject;

/**
 * <h1>Recommended Shipping Label</h1>
 * 
 * @author dk
 *
 */
@Entity
@Table(name="RECOMMENDED_SHIPMENT")
public class RecommendedLabel extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private RateAdapter rate;			// Recommended shipping rate
	
	
	
/**
 * <p>Create a new instance</p>
 * @param rate
 * @return
 */
	public static RecommendedLabel create(RateAdapter rate) {
		try {
			if(rate == null)
				throw new IllegalArgumentException("Rate cannot be null");
			RecommendedLabel shipment = new RecommendedLabel();			
			shipment.rate = rate;
			return shipment;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Embedded
	public RateAdapter getRate() {return rate;}
	public void setRate(RateAdapter rate) {	this.rate = rate;}
	
	
	
}
