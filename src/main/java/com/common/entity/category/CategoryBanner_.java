package com.common.entity.category;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.support.embeded.ImagePath;

@StaticMetamodel(CategoryBanner.class)
public class CategoryBanner_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<CategoryBanner, GroupCategory> group;
	public static volatile SingularAttribute<CategoryBanner, ImagePath> path;
	public static volatile SingularAttribute<CategoryBanner, Boolean> primary;
}
