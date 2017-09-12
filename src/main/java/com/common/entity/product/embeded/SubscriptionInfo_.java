package com.common.entity.product.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SubscriptionInfo.class)
public class SubscriptionInfo_ {
	

	public static volatile SingularAttribute<SubscriptionInfo, Integer> nos;
	public static volatile SingularAttribute<SubscriptionInfo, Integer> percentSave;
	public static volatile SingularAttribute<SubscriptionInfo, Integer> frequency;
	
	
}