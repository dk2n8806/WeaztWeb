package com.common.entity.stripe;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.stripe.ChargeAdapter;
import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(StripeCharge.class)
public class StripeCharge_ extends AbstractPersistenceObject_{

	
	public static volatile SingularAttribute<StripeCharge, ChargeAdapter>  chargeAdapter;
	public static volatile SingularAttribute<StripeCharge, StripeCustomer> stripeCustomer;
}
