package com.common.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;


@Entity
@Table(name="GEOLOCATION")
public class GeoLocation extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private long longitude;
	private long latitude;
	private Address address;
	
	
	public static GeoLocation createEntityInstance(long longitude, long latitude, Address address) 
	{
		try {
			GeoLocation location = new GeoLocation();
			location.latitude = latitude;
			location.longitude = longitude;
			location.address = address;
			return location;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Column(name="LONGITUDE", nullable=false, updatable=false)
	public long getLongitude() {	return longitude;}
	public void setLongitude(long longitude) {	this.longitude = longitude;}
	

	@Column(name="LAITUDE", nullable=false, updatable=false)
	public long getLatitude() {return latitude;}
	public void setLatitude(long latitude) {	this.latitude = latitude;}
	
	

	@JoinColumn(name="ADDRESS_ID", nullable=false, updatable=false)
	public Address getAddress() {	return address;}
	public void setAddress(Address address) {this.address = address;}
	
	
	
}
