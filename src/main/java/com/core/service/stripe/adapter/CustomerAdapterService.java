package com.core.service.stripe.adapter;

import java.util.List;

import com.common.adapter.stripe.CardAdapter;
import com.common.adapter.stripe.CustomerAdapterAttribute;
import com.common.adapter.stripe.CustomerAdapter;
import com.core.service.stripe.adapter.impl.CustomerAdapterServiceImpl;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;




/***********************************************************************
 * 
 * @author dk
 *
 */
public interface CustomerAdapterService {
	

	/** {@link CustomerAdapterServiceImpl#create(CustomerAdapterAttribute)} */
	CustomerAdapter create(CustomerAdapterAttribute adapter) 
				throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;
	
	
	
	
	/** {@link CustomerAdapterServiceImpl#lookup(String)} */
	CustomerAdapter lookup(String customerTokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
	
	
	
	/** {@link CustomerAdapterServiceImpl#update(String, CustomerAdapterAttribute)} */
	CustomerAdapter update(String customerTokenId, CustomerAdapterAttribute adapter)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
	
	
	
	
	
	/** {@link CustomerAdapterServiceImpl#getCards(String)} */
	List<CardAdapter> getCards(String customerTokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	

	
	
	
	/** {@link CustomerAdapterServiceImpl#delete(String)} */
	void delete(String customerTokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
}
