package com.common.entity.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;

@Entity
@Table(name="COMMISSION_RATE")
public class CommissionRate extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_RATE = 1000;
	private int rateValue;
	
	

	
	public static CommissionRate create() {
		return createEntityInstace(DEFAULT_RATE);
	}
	
	public static CommissionRate createEntityInstace(int rateValue) {
		try {
			if(rateValue < 0 || rateValue > 2000) 
				throw new IllegalArgumentException("Commission_rate value must be [0% - 10%");

			CommissionRate rate = new CommissionRate();
			rate.rateValue = rateValue;
			return rate;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Column(name="RATE_VALUE", nullable=false)
	public int getRateValue() {return rateValue;}
	public void setRateValue(int rateValue) {	this.rateValue = rateValue;}
	
	
}
