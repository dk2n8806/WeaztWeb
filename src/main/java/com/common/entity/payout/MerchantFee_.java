package com.common.entity.payout;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.merchant.Merchant;
import com.common.type.FeeStatus;


@StaticMetamodel(MerchantFee.class)
public class MerchantFee_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<MerchantFee, Merchant> merchant;
	public static volatile SingularAttribute<MerchantFee, Integer> amount;
	public static volatile SingularAttribute<MerchantFee, FeeStatus> status;
}
