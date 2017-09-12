package com.common.entity.support.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(BirthDate.class)
public class BirthDate_ {

	public static volatile SingularAttribute<BirthDate, Integer> date;
	public static volatile SingularAttribute<BirthDate, Integer> month;
	public static volatile SingularAttribute<BirthDate, Integer> year;
}
