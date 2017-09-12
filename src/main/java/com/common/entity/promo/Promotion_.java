package com.common.entity.promo;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(Promotion.class)
public class Promotion_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<Promotion, String> code;
	public static volatile SingularAttribute<Promotion, Date> startOn;
	public static volatile SingularAttribute<Promotion, Date> endOn;
	public static volatile SingularAttribute<Promotion, Boolean> active;
	public static volatile SingularAttribute<Promotion, Integer> value;
	public static volatile SingularAttribute<Promotion, PromotionKind> kind;
	public static volatile SingularAttribute<Promotion, PromotionTarget> target;
	
}
