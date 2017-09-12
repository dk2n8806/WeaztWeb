package com.common.entity.payout;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.merchant.Merchant;
import com.common.type.PayoutStatus;

@StaticMetamodel(Payout.class)
public class Payout_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<Payout, Integer> amount;
	public static volatile SingularAttribute<Payout, PayoutStatus> status;
	public static volatile SingularAttribute<Payout, Merchant> merchant;
}
