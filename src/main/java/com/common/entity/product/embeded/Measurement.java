package com.common.entity.product.embeded;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.common.type.DistanceUnit;

/**
 * @author dk
 *
 */
@Embeddable
public class Measurement {

	private double height;
	private double width;
	private double length;
	private DistanceUnit unit;
	

	public static Measurement create(double width, double height, double length, DistanceUnit unit) {
		try {

			if(width <= 0 || height <= 0 || length <= 0 || unit == null) 
				throw new IllegalArgumentException("Invalid measurement data");

			DecimalFormat df = new DecimalFormat("#.#");
			Measurement measurement = new Measurement();
			measurement.width = Double.valueOf(df.format(width));
			measurement.height = Double.valueOf(df.format(height));
			measurement.length = Double.valueOf(df.format(length));
			measurement.unit = unit;
			return measurement;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\theight: " + height + "\n\twidth: " + width + "\n\tlength: " + length
				+ "\n\tunit: " + unit + "\n}";
	}




	@Column(name="LENGTH")
	public double getLength() {		return length;	}
	public void setLength(double length) {	this.length = length;	}
	
	
	@Column(name="HEIGHT")
	public double getHeight() {		return height;	}
	public void setHeight(double height) {		this.height = height;	}
	

	@Column(name="WIDTH")
	public double getWidth() {		return width;	}
	public void setWidth(double width) {	this.width = width;}
	

	@Enumerated(EnumType.STRING)
	@Column(name="MEASUREMENT_UNIT")
	public DistanceUnit getUnit() {		return unit;	}
	public void setUnit(DistanceUnit unit) {		this.unit = unit;	}


}
