package com.common.adapter.stripe;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ChargeAdapter.class)
public class ChargeAdapter_ {


	public static SingularAttribute<ChargeAdapter, BigDecimal> chargeAmount;
	public static SingularAttribute<ChargeAdapter, String> tokenId;
	
}
