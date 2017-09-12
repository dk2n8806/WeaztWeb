package com.common.adapter.shippo;

import java.util.HashMap;
import java.util.Map;

import com.admin.resource.WeaztSupportEmail;
import com.common.entity.address.Address;
import com.common.type.USAStates;


public class AddressAdapterAttribute {

	
	private String name;
	private String email;
	private String street;	
	private String city;	
	private USAStates state;
	private String country;
	private String zipcode;
	private ShippoPurpose purpose;
	
	
	
	


	

	public AddressAdapterAttribute(String name
			, String street, String city, USAStates state, String country, String zipcode) 
	{
			super();
			this.name = name;
			this.street = street;
			this.city = city;
			this.state = state;
			this.country = country;
			this.zipcode = zipcode;
	}

	

	public AddressAdapterAttribute(String name
			, String street, String city, USAStates state, String zipcode) 
	{
			super();
			this.name = name;
			this.street = street;
			this.city = city;
			this.state = state;
			this.country = Address.COUNTRY_DEFAULT;
			this.zipcode = zipcode;
	}




/**
 * @return map of necessary address_data
 */
	public Map<String, Object>  getAddressData() {
		if(name == null) {
			throw new IllegalArgumentException("required name");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("validate", true);
		params.put("name", 	this.name);
		params.put("street1", 	this.street);
		params.put("city", 		this.city);
		params.put("state", 		this.state.getState());
		params.put("zip", 		this.zipcode);
		params.put("country", country);
	
		if(purpose == null) {
			params.put("object_purpose", ShippoPurpose.PURCHASE);
		} else {
			params.put("object_purpose", purpose);
		}
		if(email != null) {
			params.put("email", email);
		} else {
			params.put("email", WeaztSupportEmail.INFO_EMAIL);
		}
		return params;
	}
	
	
	
	public ShippoPurpose getPurpose() {		return purpose;}
	public void setPurpose(ShippoPurpose purpose) {	this.purpose = purpose;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
}
