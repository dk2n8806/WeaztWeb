package com.common.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AbstractPersistenceObject.class)
public class AbstractPersistenceObject_ {
	

	public static volatile SingularAttribute<AbstractPersistenceObject, Long> id;
	public static volatile SingularAttribute<AbstractPersistenceObject, Integer> version;
	public static volatile SingularAttribute<AbstractPersistenceObject, Date> createdOn;
	

}
