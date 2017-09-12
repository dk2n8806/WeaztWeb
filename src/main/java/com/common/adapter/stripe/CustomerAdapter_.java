package com.common.adapter.stripe;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerAdapter.class)
public class CustomerAdapter_ {

	public static SingularAttribute<CustomerAdapter, String> tokenId;
	public static SingularAttribute<CustomerAdapter, CardAdapter> cardAdapter;
}
