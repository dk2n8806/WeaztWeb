package com.common.entity.payout;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.stripe.StripeTransfer;

@StaticMetamodel(PayoutTransaction.class)
public class PayoutTransaction_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<PayoutTransaction, StripeTransfer> transfer;
}
