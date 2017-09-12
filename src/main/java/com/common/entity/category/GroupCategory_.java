package com.common.entity.category;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(GroupCategory.class)
public class GroupCategory_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<GroupCategory, String> name;
	public static volatile SingularAttribute<GroupCategory, Boolean> active;
}
