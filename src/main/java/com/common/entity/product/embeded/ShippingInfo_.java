package com.common.entity.product.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.type.ShippingType;

@StaticMetamodel(ShippingInfo.class)
public class ShippingInfo_ {
	

	public static volatile SingularAttribute<ShippingInfo, Integer> shippingCost;
	public static volatile SingularAttribute<ShippingInfo, ShippingType> type;

}
