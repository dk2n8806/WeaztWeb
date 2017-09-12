package com.common.entity.product;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;


/** 
 * 
 * @author dk2n_
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class ParcelAdapter extends AbstractPersistenceObject {
	

	private static final long serialVersionUID = 1L;
	private Weight weight;
	private Measurement measurement;

	
	@Embedded
	public Weight getWeight() {		return weight;	}
	public void setWeight(Weight weight) {	this.weight = weight;}
	
	@Embedded
	public Measurement getMeasurement() {	return measurement;	}
	public void setMeasurement(Measurement measurement) {	this.measurement = measurement;}
	
	
	
	
	
	
	
}
