package com.core.service.stripe.adapter;

import com.common.adapter.stripe.TransferAdapterAttribute;
import com.common.adapter.stripe.TransferAdapter;
import com.core.service.stripe.adapter.impl.TransferAdapterServiceImpl;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;


/********************************************
 * 
 * @author dk
 *
 */
public interface TransferAdapterService {


	/** {@link TransferAdapterServiceImpl#create(TransferAdapterAttribute)} */
	TransferAdapter create(TransferAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, CardException, APIException;
	
	
	/** {@link TransferAdapterServiceImpl#lookup(String)} */
	TransferAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException;
	
}
