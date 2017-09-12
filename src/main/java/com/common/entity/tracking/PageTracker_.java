package com.common.entity.tracking;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(PageTracker.class)
public class PageTracker_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<PageTracker, Account> account;
	public static volatile SingularAttribute<PageTracker, String> ip;
}
