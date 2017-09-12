package com.common.entity.product.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Embeddable
public class SubscriptionInfo {

	private static final Logger logger = LogManager.getLogger();
	private int nos;
	private int percentSave;
	private int frequency;
	
	
	
	public static SubscriptionInfo create(int nos, int percentSave, int frequency) 
	{
		try {
			if(nos  < 2 || nos > 9) {
				throw new IllegalArgumentException("Number of shipment must be within [2-9]");
			}
			if(percentSave < 0 || percentSave > 9999) {
				throw new IllegalArgumentException("percent_save must be within [0 - 9999]");
			}
			if(frequency < 7 || frequency > 56) {
				throw new IllegalArgumentException("Frequency must be within [7 - 56]");
			}

			SubscriptionInfo s = new SubscriptionInfo();

			s.nos = nos;
			s.percentSave = percentSave;
			s.frequency = frequency;
			return s;
		} catch(IllegalArgumentException e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tnos: " + nos + "\n\tpercentSave: " + percentSave + "\n\tfrequency: "
				+ frequency + "\n}";
	}








	@Column(name="FREQUENCY", nullable=false)
	public int getFrequency() {		return frequency;	}
	public void setFrequency(int frequency) {	this.frequency = frequency;}
	
	
	
	@Column(name="NOS", nullable=false)
	public int getNos() {	return nos;}
	public void setNos(int nos) {		this.nos = nos;}
	
	
	@Column(name="PERCENT_SAVE", nullable=false)
	public int getPercentSave() {	return percentSave;}
	public void setPercentSave(int percentSave) {this.percentSave = percentSave;}
	
	
}
