package com.common.entity.notification;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.payment.Payment;

@StaticMetamodel(VoidSubscriptionPaymentNotification.class)
public class VoidSubscriptionPaymentNotification_ extends Notification_ {

	public static volatile SingularAttribute<VoidSubscriptionPaymentNotification, Payment> payment;
}
