package com.common.entity.promo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.common.entity.AbstractPersistenceObject;
import com.common.util.RandomStringGenerator;

@Entity
@Table(name="PROMOTION")
public class Promotion extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

	private String code;
	private Date startOn;
	private Date endOn;
	private boolean isActive;
	private int value;

	
	
	protected Promotion(){}
	public static Promotion create(int value, Date startOn, Date endOn) {
		return create(new RandomStringGenerator(5).nextString(), value, startOn, endOn);
	}
	
	public static Promotion create(String code, int value, Date startOn, Date endOn) {
		try {
			if(code == null || code.length() < 5) 
				throw new IllegalArgumentException("Invalid promotion code");
			if(value < 0 || value > 5000) 
				throw new IllegalArgumentException("Invalid promotion value");
			if(startOn.compareTo(endOn) >= 0) 
				throw new IllegalArgumentException("Invalid promotion dates");
			
			Promotion promotion = new Promotion();
			promotion.code = code.toUpperCase();
			promotion.value = value;
			promotion.startOn = startOn;
			promotion.endOn = endOn;
			promotion.isActive = true;
			
			return promotion;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
@Override
	public String toString() {
		return getClass().getName() + " {\n\tcode: " + code + "\n\tstartOn: " + startOn + "\n\tendOn: " + endOn
				+ "\n\tisActive: " + isActive + "\n\tvalue: " + value + "\n\tgetId: " + getId() + "\n\tgetCreatedOn: "
				+ getCreatedOn() + "\n}";
	}
/** :::
 * 
 * Verify the usability of the promotion.
 * 
 * The promotion code is usebale iff
 * 	- code is active
 * 	- the date using the code is after or equal to start_on
 * 	- the date using the code is before or equal to end_on
 * 
 ** ::: */
	@Transient
	public boolean isAppliable() {
		return this.isActive 
				&& (new Date().compareTo(this.startOn) >= 0)
				&& (new Date().compareTo(this.endOn) <= 0);
	}

	
	
	
	
	
	@Column(name="VALUE", nullable=false)
	public int getValue() {	return value;}
	public void setValue(int value) {	this.value = value;}

	
	
	
	
	@Column(name="CODE", nullable=false, updatable=false, unique=true)
	public String getCode() {	return code;}
	public void setCode(String code) {		this.code = code;	}

	
	
	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {		return isActive;	}
	public void setActive(boolean isActive) {	this.isActive = isActive; }

	
	
	

	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_ON")
	public Date getStartOn() {	return startOn;}
	public void setStartOn(Date startOn) {		this.startOn = startOn;}

	
	

	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_ON")
	public Date getEndOn() {	return endOn;}
	public void setEndOn(Date endOn) {	this.endOn = endOn;}
	
	
	
}
