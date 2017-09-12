package com.common.entity.account;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.support.embeded.ImagePath;

@StaticMetamodel(Avatar.class)
public class Avatar_ extends AbstractPersistenceObject_ {


	public static volatile SingularAttribute<Avatar, ImagePath> imagePath;
}
