package com.common.adapter.shippo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;
import com.common.type.DistanceUnit;
import com.common.type.MassUnit;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="SHIPPO_PARCEL_ADAPTER")
public class ShippoParcelAdapter extends com.common.entity.product.ParcelAdapter {

	private static final long serialVersionUID = 1L;
	private String token;
	
	
	
/**
 * Create an in-app parcel_adapter
 * 
 * Convert com.shippo.model.parcel to in-app parcel_adapter
 * 
 * @param parcel com.shippo.model.parcel Parcel Object
 * @return parcel_adapter
 */
	public static ShippoParcelAdapter create(com.shippo.model.Parcel parcel) {

		try {
			if(parcel == null) 
				throw new IllegalArgumentException("Shippo_parcel is required");
			if(!parcel.getObjectState().equals("VALID")) 
				throw new IllegalArgumentException("Invalid Shipping_parcel state");
			
			
			double width  = Double.valueOf(String.valueOf(parcel.getWidth()));
			double height = Double.valueOf(String.valueOf(parcel.getHeight()));
			double length = Double.valueOf(String.valueOf(parcel.getLength()));
			double weight = Double.valueOf(String.valueOf(parcel.getWeight()));
			DistanceUnit distanceUnit = DistanceUnit.lookup(String.valueOf(parcel.getDistanceUnit()));
			MassUnit massUnit = MassUnit.lookup(String.valueOf(parcel.getMassUnit()));
			

			ShippoParcelAdapter adapter = new ShippoParcelAdapter();
			adapter.setWeight(Weight.create(weight, massUnit));
			adapter.setMeasurement(Measurement.create(width, height, length, distanceUnit));
			adapter.token = parcel.getObjectId();
			
			return adapter;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Column(name="PARCEL_TOKEN", unique=true)
	public String getToken() {		return token;	}
	public void setToken(String token) {	this.token = token;}


}
