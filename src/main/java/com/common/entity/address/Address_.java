package com.common.entity.address;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.type.USAStates;

@StaticMetamodel(Address.class)
public class Address_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, USAStates> state;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> zipcode;
}
