package com.common.entity.subscription;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(Unsubscription.class)
public class Unsubscription_ extends AbstractPersistenceObject_
{

	public static volatile SingularAttribute<Unsubscription, Subscription> subscription;
}
