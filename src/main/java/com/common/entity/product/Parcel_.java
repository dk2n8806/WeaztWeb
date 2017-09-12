package com.common.entity.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(Parcel.class)
public class Parcel_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<Parcel, ParcelAdapter> parcelAdapter;
	public static volatile SingularAttribute<Parcel, Product> product;
	public static volatile SingularAttribute<Parcel, Boolean> active;
}
