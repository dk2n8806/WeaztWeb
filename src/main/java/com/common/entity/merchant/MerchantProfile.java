package com.common.entity.merchant;


import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.address.Address;
import com.common.entity.support.embeded.Phone;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="MERCHANT_PROFILE")
public class MerchantProfile extends AbstractPersistenceObject {

	
	private static final long serialVersionUID = 1L;
	private Phone phone;
	private Address address;
	
	
	
	/** :::
	 * Create a new merchant profile for the merchant
	 * 
	 * ::: */
	public static MerchantProfile createEntityInstance(Phone phone, Address address) 
	{
		try{
			if(phone == null || address == null) {
				throw new IllegalArgumentException("require phone and address");
			}
			MerchantProfile profile = new MerchantProfile();
			profile.address = address;
			profile.phone = phone;
			return profile;
		} catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	
	@Embedded
	public Phone getPhone() {return phone;}
	public void setPhone(Phone phone) {this.phone = phone;}
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID")
	public Address getAddress() {return address;}
	public void setAddress(Address address) {this.address = address;}
	
	
	
	
	
	
	
}
