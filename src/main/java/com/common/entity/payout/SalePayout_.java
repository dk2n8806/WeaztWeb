package com.common.entity.payout;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.order.OrderBundle;

@StaticMetamodel(SalePayout.class)
public class SalePayout_ extends Payout_ {

	public static volatile SingularAttribute<SalePayout, OrderBundle> orderBundle;
}
