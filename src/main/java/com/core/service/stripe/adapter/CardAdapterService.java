package com.core.service.stripe.adapter;

import com.common.adapter.stripe.CardAdapter;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

public interface CardAdapterService {

	CardAdapter lookup(String tokenId) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
}
