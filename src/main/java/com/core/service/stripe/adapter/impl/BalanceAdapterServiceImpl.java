package com.core.service.stripe.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.BalanceAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.BalanceAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Balance;


@Service("stripeBalanceAdapterServiceImpl")
public class BalanceAdapterServiceImpl implements BalanceAdapterService {

	@Override
	public BalanceAdapter lookup() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		Balance balance = Balance.retrieve(StripeRequestManage.getOptions());
		return BalanceAdapter.convert(balance);
	}

}
