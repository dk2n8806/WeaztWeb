package com.common.entity.order;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.subscription.Subscription;

@StaticMetamodel(OrderTransaction.class)
public class OrderTransaction_ extends AbstractPersistenceObject_ {

	
	public static volatile SingularAttribute<OrderTransaction, OrderBundle> orderBundle;
	public static volatile SingularAttribute<OrderTransaction, Shipment> shipment;
	public static volatile SingularAttribute<OrderTransaction, Subscription> subscription;
	public static volatile SingularAttribute<OrderTransaction, Integer> total;


}
