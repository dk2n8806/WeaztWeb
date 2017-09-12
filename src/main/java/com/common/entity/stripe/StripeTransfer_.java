package com.common.entity.stripe;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.stripe.TransferAdapter;
import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(StripeTransfer.class)
public class StripeTransfer_  extends AbstractPersistenceObject_{
	
	public static volatile SingularAttribute<StripeTransfer, TransferAdapter>  transferAdapter;
	public static volatile SingularAttribute<StripeTransfer, StripeAccount>  stripeAccount;

}
