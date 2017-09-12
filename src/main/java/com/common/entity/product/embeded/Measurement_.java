package com.common.entity.product.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.type.DistanceUnit;

@StaticMetamodel(Measurement.class)
public class Measurement_ {
	
	
	public static volatile SingularAttribute<Measurement, Float> height;
	public static volatile SingularAttribute<Measurement, Float> width;
	public static volatile SingularAttribute<Measurement, Float> length;
	public static volatile SingularAttribute<Measurement, DistanceUnit> unit;
	

}
