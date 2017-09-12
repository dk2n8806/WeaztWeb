package com.common.entity.subscription.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(ValuePerShipment.class)
public class ValuePerShipment_ {

	public static volatile SingularAttribute<ValuePerShipment, Integer> productValue;
	public static volatile SingularAttribute<ValuePerShipment, Integer> taxCost;
	public static volatile SingularAttribute<ValuePerShipment, Integer> subscriptionValue;
	public static volatile SingularAttribute<ValuePerShipment, Integer> shippingCost;
	public static volatile SingularAttribute<ValuePerShipment, Integer> subscriptionCost;

	
}
