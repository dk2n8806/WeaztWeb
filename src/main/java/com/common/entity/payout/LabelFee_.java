package com.common.entity.payout;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.order.GeneratedLabel;

@StaticMetamodel(LabelFee.class)
public class LabelFee_ extends MerchantFee_{

	
	public static volatile SingularAttribute<LabelFee, GeneratedLabel> label;
}
