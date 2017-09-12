package com.common.entity.subscriber;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;
import com.common.entity.product.Product;

@StaticMetamodel(Bookmark.class)
public class Bookmark_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<Bookmark, Account> account;
	public static volatile SingularAttribute<Bookmark, Product> product;
	public static volatile SingularAttribute<Bookmark, Boolean> active;
}
