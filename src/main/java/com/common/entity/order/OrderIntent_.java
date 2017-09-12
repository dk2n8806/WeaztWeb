package com.common.entity.order;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;

@StaticMetamodel(OrderIntent.class)
public class OrderIntent_ extends AbstractPersistenceObject_ {

	public static volatile SingularAttribute<OrderIntent, Subscription> subscription;
	public static volatile SingularAttribute<OrderIntent, Date> scheduledDate;
	public static volatile SingularAttribute<OrderIntent, OrderIntentStatus> status;
}
