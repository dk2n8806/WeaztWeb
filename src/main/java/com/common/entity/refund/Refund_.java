package com.common.entity.refund;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(Refund.class)
public class Refund_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<Refund, Integer> amount;
	public static volatile SingularAttribute<Refund, Account> account;
	public static volatile SingularAttribute<Refund, RefundStatus> status;
	
}
