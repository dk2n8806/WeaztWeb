package com.common.entity.category;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(Category.class)
public class Category_ extends AbstractPersistenceObject_ {


	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, Boolean> active;
	public static volatile SingularAttribute<Category, GroupCategory> group;
}
