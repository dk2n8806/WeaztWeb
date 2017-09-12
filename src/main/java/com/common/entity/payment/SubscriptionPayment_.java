package com.common.entity.payment;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;

@StaticMetamodel(SubscriptionPayment.class)
public class SubscriptionPayment_  extends Payment_{

	public static volatile SingularAttribute<SubscriptionPayment, Account> account;
	public static volatile SingularAttribute<SubscriptionPayment, Subscription> subscription;
	
}
