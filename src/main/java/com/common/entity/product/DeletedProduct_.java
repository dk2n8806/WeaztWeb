package com.common.entity.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(DeletedProduct.class)
public class DeletedProduct_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<DeletedProduct, Product> product;
}
