package com.common.entity.stripe;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.stripe.AccountAdapter;
import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.merchant.Merchant;

@StaticMetamodel(StripeAccount.class)
public class StripeAccount_ extends AbstractPersistenceObject_{
	
	public static volatile SingularAttribute<StripeAccount, AccountAdapter>  accountAdapter;
	public static volatile SingularAttribute<StripeAccount, Merchant> merchant;
	public static volatile SingularAttribute<StripeAccount, StripeStatus>  status;
	
}
