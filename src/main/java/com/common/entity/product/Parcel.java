package com.common.entity.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.common.entity.AbstractPersistenceObject;

@Entity
@Table(name="PARCEL")
public class Parcel extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;
	private Product product;
	private ParcelAdapter parcelAdapter;
	private boolean isActive;
	
	
	public static Parcel create(Product product, ParcelAdapter parcelAdapter) {
		try {
			if(product == null)
				throw new IllegalArgumentException("Product entity is required");
			if(parcelAdapter == null)
				throw new IllegalArgumentException("Parcel adapter is required");
			
			Parcel parcel = new Parcel();
			parcel.parcelAdapter = parcelAdapter;
			parcel.product = product;
			parcel.isActive = true;
			return parcel;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PARCEL_ADAPTER_ID")
	public ParcelAdapter getParcelAdapter() {return parcelAdapter;}
	public void setParcelAdapter(ParcelAdapter parcelAdapter) {	this.parcelAdapter = parcelAdapter;}
	
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", updatable=false, nullable=false)
	public Product getProduct() {		return product;}
	public void setProduct(Product product) {		this.product = product;}
	

	@Type(type="yes_no")
	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {	return isActive;}
	public void setActive(boolean isActive) {this.isActive = isActive;}
	
}
