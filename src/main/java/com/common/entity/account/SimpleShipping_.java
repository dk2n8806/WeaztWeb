package com.common.entity.account;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.address.Address;

@StaticMetamodel(SimpleShipping.class)
public class SimpleShipping_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<SimpleShipping, Account> account;
	public static volatile SingularAttribute<SimpleShipping, Address> address;
	public static volatile SingularAttribute<SimpleShipping, Boolean> primary;
	public static volatile SingularAttribute<SimpleShipping, Boolean> active;
	
	
}
