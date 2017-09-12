package com.common.entity.promo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(NewLetter.class)
public class NewLetter_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<NewLetter, String> email;
}
