package com.common.adapter.stripe.util;

import com.admin.resource.WeaztAppResourceKeys;
import com.stripe.net.RequestOptions;

public class StripeRequestManage {


	
	public static RequestOptions getOptions() {
		RequestOptions options = RequestOptions.builder()
				.setApiKey(WeaztAppResourceKeys.StripeResource.STRIPE_SECRET_KEY)
				.build();
		return options;
	}
	
	public static RequestOptions getOptions(String str) {
		return RequestOptions.builder()
						.setApiKey(WeaztAppResourceKeys.StripeResource.STRIPE_SECRET_KEY)
						.setStripeAccount(str)
						.build();
	}
	
	
}
