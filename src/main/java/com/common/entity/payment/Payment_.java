package com.common.entity.payment;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.type.PaymentStatus;

@StaticMetamodel(Payment.class)
public class Payment_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<Payment, Integer> 						amount;
	public static volatile SingularAttribute<Payment, PaymentStatus> 		status;
	public static volatile SingularAttribute<Payment, Boolean> 					requested;
	public static volatile SingularAttribute<Payment, PaymentTransaction> transaction;
	
}
