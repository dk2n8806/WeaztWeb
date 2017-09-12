package com.common.entity.subscription.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SubscriptionRating.class)
public class SubscriptionRating_ {
	
	public static volatile SingularAttribute<SubscriptionRating, Boolean> recommended;
	public static volatile SingularAttribute<SubscriptionRating, Boolean> favorited;
}
