package com.core.service.stripe.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.TokenAdapterAttribute;
import com.common.adapter.stripe.TokenAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.TokenAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Token;


/************************************************************
 * 
 * @author dk
 *
 */
@Service("stripeTokenAdapterServiceImpl")
public class TokenAdapterServiceImpl implements TokenAdapterService {

	
	
/*************************************************************
 * 
 * {@link TokenAdapterService#create(TokenAdapterAttribute)}
 */
	@Override
	public TokenAdapter create(TokenAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, CardException, APIException 
	{
		Token token = Token.create(adapter.getMetadata(), StripeRequestManage.getOptions());
		return TokenAdapter.create(token);
	}

}
