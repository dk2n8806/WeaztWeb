package com.common.entity.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;

@StaticMetamodel(ParcelAdapter.class)
public class ParcelAdapter_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<ParcelAdapter, Measurement> measurement;
	public static volatile SingularAttribute<ParcelAdapter, Weight> weight;
}
