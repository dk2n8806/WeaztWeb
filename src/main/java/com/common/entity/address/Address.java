package com.common.entity.address;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang.WordUtils;

import com.common.entity.AbstractPersistenceObject;
import com.common.type.USAStates;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Address extends AbstractPersistenceObject {

	private static final long serialVersionUID = -5693599744758832000L;
	
	public static final String COUNTRY_DEFAULT = "US";

	private String street;	
	private String city;	
	private USAStates state;
	private String country;
	private String zipcode;
	
 
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tstreet: " + street + "\n\tcity: " + city + "\n\tstate: " + state
				+ "\n\tcountry: " + country + "\n\tzipcode: " + zipcode + "\n}";
	}


	@Override
	public boolean equals(Object obj) {
		if(this == obj) { return true; }
		if(obj == null || getClass() != obj.getClass()) { return false; }
		
		Address other = (Address) obj;
		if(street != null ? !street.equals(other.getStreet()) : other.getStreet() == null) 
			return false;
		if(city != null ? !city.equals(other.getCity()) : other.getCity() == null)
			return false;
		if(state != null ? !state.equals(other.getState()) : other.getState() == null) 
			return false;
		if(country != null ? !country.equals(other.getCountry()) : other.getCountry() == null) 
			return false;
		if(zipcode != null ? !zipcode.equals(other.getZipcode()) : other.getZipcode() == null) 
			return false;
		return super.equals(obj);
	}
	
	
	@Override
	public int hashCode() {
		int result =  street != null ? street.hashCode() : 0;
		result = 31 * result + (city 		!= null ? city.hashCode() : 0);
		result = 31 * result + (country 	!= null ? country.hashCode() : 0);
		result = 31 * result + (state 	  	!= null ? state.hashCode() : 0);
		result = 31 * result + (zipcode 	!= null ? zipcode.hashCode() : 0);
		return result;
	}
	
	

	@Column(name="STREET", nullable=true)
	public String getStreet() {		return street;	}
	public void setStreet(String street) {		this.street = WordUtils.capitalizeFully(street);	}

	

	@Column(name="CITY", nullable=true)
	public String getCity() {		return city;	}
	public void setCity(String city) {		this.city = WordUtils.capitalizeFully(city);	}

	

	@Enumerated(EnumType.STRING)
	@Column(name="STATE", nullable=true)
	public USAStates getState() {	return state;}
	public void setState(USAStates state) {this.state = state;}
	

	@Column(name="COUNTRY", nullable=true)
	public String getCountry() {		return country;	}
	public void setCountry(String country) {		this.country = WordUtils.capitalize(country);	}

	

	@Column(name="ZIPCODE", nullable=true)
	public String getZipcode() {		return zipcode;	}
	public void setZipcode(String zipcode) {		this.zipcode = WordUtils.capitalizeFully(zipcode);	}
}