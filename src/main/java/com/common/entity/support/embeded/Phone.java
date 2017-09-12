package com.common.entity.support.embeded;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import com.common.util.PhoneNumberFormat;

@Embeddable
public class Phone implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String number;
	private String displayableNumber;
	private boolean isActive;
	private boolean isVerified;
	
	
	
	/** :::
	 * Create a new phone entity instance
	 * 
	 * @param number
	 * @return phone entity or null
	 * ::: */
	public static Phone create(String number) {
		try {
			PhoneNumberFormat formater = new PhoneNumberFormat(number);
			Phone phone = new Phone();
			phone.number = formater.getPrivateNumber();
			phone.displayableNumber = formater.getFirst3Digits();
			phone.isActive=true;
			phone.isVerified=false;
			return phone;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	

	@Column(name="IS_ACTIVE")
	public boolean isActive() {return isActive;}
	public void setActive(boolean isActive) {this.isActive = isActive;}


	@Column(name="IS_VEIFIED")
	public boolean isVerified() {return isVerified;}
	public void setVerified(boolean isVerified) {this.isVerified = isVerified;}


	@Column(name="DISPLAYABLE_PHONE_NUMBER")
	public String getDisplayableNumber() {return displayableNumber;}
	public void setDisplayableNumber(String displayableNumber) {this.displayableNumber = displayableNumber;}

	
	@Column(name="PHONE_NUMBER")
	public String getNumber() {	return number;}
	public void setNumber(String number) {	this.number = number;}
	
}
