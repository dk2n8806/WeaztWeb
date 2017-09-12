package com.common.adapter.shippo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.address.Address;
import com.common.type.USAStates;

@Entity
@Table(name="SHIPPO_ADDRESS_ADAPTER")
public class ShippoAddressAdapter extends Address{

	private static final long serialVersionUID = 1L;

	private String name;
	private String token;
	private String service;
	
	public static ShippoAddressAdapter create(com.shippo.model.Address shippoAddress) {
		try {
			
			if(!String.valueOf(shippoAddress.getObjectState()).equals("VALID") ) 
				throw new IllegalArgumentException("Invalid state address");

			String name = String.valueOf(shippoAddress.getName());
			String street = String.valueOf(shippoAddress.getStreet1());
			String city = String.valueOf(shippoAddress.getCity());
			String zipcode = String.valueOf(shippoAddress.getZip());
			String country = String.valueOf(shippoAddress.getCountry());
			USAStates state = USAStates.lookup(String.valueOf(shippoAddress.getState()));
			String tokenId = shippoAddress.getObjectId();
			
			
			ShippoAddressAdapter adapter = new ShippoAddressAdapter();
			adapter.name = name;
			adapter.setStreet(street);
			adapter.setCity(city);
			adapter.setState(state);
			adapter.setCountry(country);
			adapter.setZipcode(zipcode);
			adapter.setToken(tokenId);
			adapter.service = "SHIPPO";
			
			return adapter;
			
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
		

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tname: " + name + "\n\ttoken: " + token + "\n\tservice: " + service
				+ "\n\ttoString: " + super.toString() + "\n}";
	}






	@Column(name="SERVICE", nullable=false, updatable=false)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	@Column(name="NAME", nullable=false, updatable=false)
	public String getName() {	return name;}
	public void setName(String name) {	this.name = name;}
	
	@Column(name="TOKEN", nullable=true, updatable=false)
	public String getToken() {	return token;}
	public void setToken(String token) {	this.token = token;}
	
}
