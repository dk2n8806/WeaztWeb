package com.common.entity.review;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.type.review.ReviewType;

@StaticMetamodel(Review.class)
public class Review_ extends AbstractPersistenceObject_{
	
	public static volatile SingularAttribute<Review, String> content;
	public static volatile SingularAttribute<Review, Product> product;
	public static volatile SingularAttribute<Review, Account> account;
	public static volatile SingularAttribute<Review, ReviewType> type;

}
