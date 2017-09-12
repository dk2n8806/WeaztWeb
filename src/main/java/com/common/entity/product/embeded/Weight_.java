package com.common.entity.product.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.type.MassUnit;

@StaticMetamodel(Weight.class)
public class Weight_ {
	
	
	public static volatile SingularAttribute<Weight, Float> weight;
	public static volatile SingularAttribute<Weight, MassUnit> unit;
	
	
}
