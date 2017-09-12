package com.common.entity.merchant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;
import com.common.type.MerchantStatus;

@StaticMetamodel(Merchant.class)
public class Merchant_  extends AbstractPersistenceObject_ 
{

	public static volatile SingularAttribute<Merchant, String> businessName;
	public static volatile SingularAttribute<Merchant, String> websiteUrl;
	public static volatile SingularAttribute<Merchant, MerchantStatus> status;
	public static volatile SingularAttribute<Merchant, Account> account;
	public static volatile SingularAttribute<Merchant, MerchantProfile> profile;
	public static volatile SingularAttribute<Merchant, CommissionRate> commissionRate;
	public static volatile SingularAttribute<Merchant, TaxRate> taxRate;
}
