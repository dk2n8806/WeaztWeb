package com.common.entity.support.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(ImagePath.class)
public class ImagePath_ {

	public static volatile SingularAttribute<ImagePath, String> path;
	public static volatile SingularAttribute<ImagePath, Boolean> active;
}
