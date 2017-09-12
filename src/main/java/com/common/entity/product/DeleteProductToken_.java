package com.common.entity.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceToken;
import com.common.entity.AbstractPersistenceToken_;

@StaticMetamodel(AbstractPersistenceToken.class)
public class DeleteProductToken_ extends AbstractPersistenceToken_{

	public static volatile SingularAttribute<DeleteProductToken, Product> product;
	
}
