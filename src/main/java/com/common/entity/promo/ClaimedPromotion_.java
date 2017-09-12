package com.common.entity.promo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(ClaimedPromotion.class)
public class ClaimedPromotion_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<ClaimedPromotion, Account> account;
	public static volatile SingularAttribute<ClaimedPromotion, Promotion> promotion;
	
	
}

