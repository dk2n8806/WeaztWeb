package com.common.entity.payment;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.stripe.StripeCharge;

@StaticMetamodel(PaymentTransaction.class)
public class PaymentTransaction_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<PaymentTransaction, StripeCharge> charge;
}
