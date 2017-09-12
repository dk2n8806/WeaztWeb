package com.core.service.stripe.adapter;

import com.common.adapter.stripe.TokenAdapterAttribute;
import com.common.adapter.stripe.TokenAdapter;
import com.core.service.stripe.adapter.impl.TokenAdapterServiceImpl;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;


/**
 * <h1>TokenAdapterService</h1>
 * 
 * <p>A service to create a card_token for an input card info</p>
 * 
 * @author dk2n_
 *
 */
public interface TokenAdapterService {

	
	/** {@link TokenAdapterServiceImpl#create(TokenAdapterAttribute)} */
	TokenAdapter create(TokenAdapterAttribute card) 
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;
}
