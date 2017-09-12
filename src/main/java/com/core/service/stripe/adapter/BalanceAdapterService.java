package com.core.service.stripe.adapter;

import com.common.adapter.stripe.BalanceAdapter;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

public interface BalanceAdapterService {

	BalanceAdapter lookup()
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
}
