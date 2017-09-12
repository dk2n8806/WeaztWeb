package com.common.entity.product.embeded;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.common.type.ShippingType;

public class ShippingInfo {

	private static final Logger logger = LogManager.getLogger();
	private Integer shippingCost;
	private ShippingType type;
	

	
	
	public static ShippingInfo create(int shippingCost, ShippingType type) {
		try {
			if(type == null) 
					throw new IllegalArgumentException("Shipping_type is required");
			
			// $0 <= shipping_cost <= $50.00
			if(shippingCost <0 || shippingCost > 5000) 					
					throw new IllegalArgumentException("Invalid shipping cost");
			

			ShippingInfo info = new ShippingInfo();
			info.type = type;
			if(type == ShippingType.FLAT) {
				info.shippingCost = shippingCost;
			} else if(type == ShippingType.FREE) {
				info.shippingCost = 0;
			} 
			return info;
		} catch(IllegalArgumentException e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			logger.error(e.getCause());
			return null;
		}
	}
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tshippingCost: " + shippingCost + "\n\ttype: " + type + "\n}";
	}





	@Enumerated(EnumType.STRING)
	@Column(name="TYPE")
	public ShippingType getType() {return type;	}
	public void setType(ShippingType type) {	this.type = type;}
	
	
	@Column(name="SHIPPING_COST")
	public Integer getShippingCost() {		return shippingCost;	}
	public void setShippingCost(Integer shippingCost) {	this.shippingCost = shippingCost;}
	
	
}
