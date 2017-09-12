package com.common.adapter.stripe;

import javax.persistence.metamodel.SingularAttribute;

public class AccountAdapter_ {

	public static SingularAttribute<AccountAdapter, String> tokenId;
	public static SingularAttribute<AccountAdapter, Boolean> transferEnabled;
	public static SingularAttribute<AccountAdapter, CardAdapter> cardAdapter;
	
}
