package com.common.entity.subscriber;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;
import com.common.entity.product.Product;

@StaticMetamodel(RecentVisited.class)
public class RecentVisited_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<RecentVisited, Account> account;
	public static volatile SingularAttribute<RecentVisited, Product> product;
	public static volatile SingularAttribute<RecentVisited, Date> lastVisited;
	public static volatile SingularAttribute<RecentVisited, Boolean> active;
	
}
