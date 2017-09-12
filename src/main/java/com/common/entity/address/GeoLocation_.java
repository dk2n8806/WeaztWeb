package com.common.entity.address;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(GeoLocation.class)
public class GeoLocation_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<GeoLocation, Long> longitude;
	public static volatile SingularAttribute<GeoLocation, Long> latitude;
	public static volatile SingularAttribute<GeoLocation, Address> address;
}
