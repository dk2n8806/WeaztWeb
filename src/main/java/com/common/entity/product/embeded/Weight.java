package com.common.entity.product.embeded;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.common.type.MassUnit;


/**
 * 
 * @author dk2n_
 *
 */
@Embeddable
public class Weight {
	
	private double weight;
	private MassUnit unit;
	

	public static Weight create(double weight, MassUnit unit) {
		try {
			if(weight <= 0 || unit == null) 
				throw new IllegalArgumentException("Invalid weight data");

			DecimalFormat df = new DecimalFormat("#.#");
			Weight w = new Weight();
			w.weight = Double.valueOf(df.format(weight));
			w.unit = unit;
			return w;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		Weight weight= Weight.create(123, MassUnit.OZ);
		System.out.println(weight);
	}
	

	@Override
	public String toString() {
		return "Weight [weight=" + weight + ", unit=" + unit + "]";
	}
	
	
	

	
	@Enumerated(EnumType.STRING)
	@Column(name="WEIGHT_UNIT")	
	public MassUnit getUnit() {	return unit;}
	public void setUnit(MassUnit unit) {		this.unit = unit;}


	
	
	@Column(name="WEIGHT")
	public double getWeight() {		return weight;	}
	public void setWeight(double weight) {	this.weight = weight;}
}
