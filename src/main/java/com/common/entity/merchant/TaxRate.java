package com.common.entity.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;

@Entity
@Table(name="TAX_RATE")
public class TaxRate extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

	private int rateValue;

	public static TaxRate create() {
		return createEntityInstance(0);
	}
	
	public static TaxRate createEntityInstance(int taxRate) {
		try {
			if(taxRate < 0) 
				throw new IllegalArgumentException("Tax_rate value must be [0 - 2000]");
			
			
			TaxRate tax 	= new TaxRate();
			tax.rateValue = taxRate;
			return tax;
 		} 
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Column(name="RATE_VALUE", nullable=false)
	public int getRateValue() {return rateValue;}
	public void setRateValue(int rateValue) {	this.rateValue = rateValue;}

	
}