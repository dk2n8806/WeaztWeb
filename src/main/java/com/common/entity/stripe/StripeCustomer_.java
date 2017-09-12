package com.common.entity.stripe;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.stripe.CustomerAdapter;
import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(StripeCustomer.class)
public class StripeCustomer_ extends AbstractPersistenceObject_ {

	public static volatile SingularAttribute<StripeCustomer, StripeStatus>  status;
	public static volatile SingularAttribute<StripeCustomer, Account> account;
	public static volatile SingularAttribute<StripeCustomer, CustomerAdapter>  customerAdapter;
	
}
