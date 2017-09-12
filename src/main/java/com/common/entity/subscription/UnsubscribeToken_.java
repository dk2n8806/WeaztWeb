package com.common.entity.subscription;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceToken_;

@StaticMetamodel(UnsubscribeToken.class)
public class UnsubscribeToken_  extends AbstractPersistenceToken_ {

	public static volatile SingularAttribute<UnsubscribeToken, Subscription> subscription;
	
}
