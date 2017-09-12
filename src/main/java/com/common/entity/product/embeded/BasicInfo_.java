package com.common.entity.product.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BasicInfo.class)
public class BasicInfo_ {


	public static volatile SingularAttribute<BasicInfo, Integer> price;
	public static volatile SingularAttribute<BasicInfo, String> 	title;
	public static volatile SingularAttribute<BasicInfo, String> 	description;
}
