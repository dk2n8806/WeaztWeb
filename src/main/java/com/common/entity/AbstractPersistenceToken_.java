package com.common.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AbstractPersistenceToken.class)
public class AbstractPersistenceToken_ extends AbstractPersistenceObject_ {

	public static volatile SingularAttribute<AbstractPersistenceToken, String> token;
	public static volatile SingularAttribute<AbstractPersistenceToken, Boolean> useable;

}
