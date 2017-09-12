package com.core.service.shippo.adapter;

import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.core.service.shippo.adapter.impl.AddressAdapterServiceImpl;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

/**
 * 
 * @author dk2n_
 *
 */
public interface AddressAdapterService {

	/** {@link AddressAdapterServiceImpl#create(AddressAdapterAttribute)} */
	ShippoAddressAdapter create(AddressAdapterAttribute adapterAttribute) 
			throws AuthenticationException, InvalidRequestException
					, APIConnectionException, APIException;
	
	
	
	/** {@link AddressAdapterServiceImpl#update(String, AddressAdapterAttribute)} */
	ShippoAddressAdapter lookup(String addressTokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
	
	
	
}
