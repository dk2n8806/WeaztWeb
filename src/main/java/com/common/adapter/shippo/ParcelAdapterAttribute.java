package com.common.adapter.shippo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;

public class ParcelAdapterAttribute  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Measurement measurement;
	private Weight weight;
	
	
	public ParcelAdapterAttribute(Measurement measurement, Weight weight) {
		this.measurement = measurement;
		this.weight = weight;
	}

	
	
	
	public Map<String, Object> getParcelData() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("length", this.measurement.getLength());
		params.put("width", this.measurement.getWidth());
		params.put("height", this.measurement.getHeight());
		params.put("distance_unit", this.measurement.getUnit().getCode());
		params.put("weight", this.weight.getWeight());
		params.put("mass_unit", this.weight.getUnit().getCode());
		
		return params;
	}
	
	
	

}
