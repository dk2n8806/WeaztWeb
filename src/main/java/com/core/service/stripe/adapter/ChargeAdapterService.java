package com.core.service.stripe.adapter;



import com.common.adapter.stripe.ChargeAdapterAttribute;
import com.common.adapter.stripe.ChargeAdapter;
import com.core.service.stripe.adapter.impl.ChargeAdapterServiceImpl;
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
public interface ChargeAdapterService {



	/** {@link ChargeAdapterServiceImpl#create(ChargeAdapterAttribute)} */ 
	ChargeAdapter create(ChargeAdapterAttribute chargeAdapter)
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;


	
	/** {@link ChargeAdapterServiceImpl#update(String, ChargeAdapterAttribute)} */
	ChargeAdapter update(String tokenId, ChargeAdapterAttribute chargeAdapter)
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;

	
	/** {@link ChargeAdapterServiceImpl#lookup(String)} */
	ChargeAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, CardException, APIException;
	
	
	
}
