package com.core.service.stripe.adapter;

import java.util.List;

import com.common.adapter.stripe.AccountAdapterAttribute;
import com.common.adapter.stripe.AccountAdapter;
import com.common.adapter.stripe.CardAdapter;
import com.common.adapter.stripe.CardTokenAdapterAttribute;
import com.core.service.stripe.adapter.impl.AccountAdapterServiceImpl;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

public interface AccountAdapterService {

	
	/** {@link AccountAdapterServiceImpl#createManagedAccount(AccountAdapterAttribute)} */
	AccountAdapter createManagedAccount(AccountAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;
	
	
	
	/** {@link AccountAdapterServiceImpl#createStandaloneAccount(String)} */
	AccountAdapter createStandaloneAccount(AccountAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, CardException, APIException;
	
	


	/** {@link AccountAdapterServiceImpl#setExternalAccount(String, CardTokenAdapterAttribute)} */
	AccountAdapter setExternalAccount(String tokenId, CardTokenAdapterAttribute adapter)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;

	
	
	
	/** {@link AccountAdapterServiceImpl#lookup(String)} */
	AccountAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
	
	
	/** {@link AccountAdapterServiceImpl#update(String, AccountAdapterAttribute)} */
	AccountAdapter update(String tokenId, AccountAdapterAttribute adapter)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
	

	
	
	/** {@link AccountAdapterServiceImpl#getCards(String)} */
	List<CardAdapter> getCards(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
	
	
}
