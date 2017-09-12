package com.core.service.shippo.adapter;

import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.adapter.shippo.ParcelAdapterAttribute;
import com.core.service.shippo.adapter.impl.ParcelAdapterServiceImpl;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

public interface ParcelAdapterService {

	
	/** Create a parcel_adapter
	 * {@link ParcelAdapterServiceImpl#create(ParcelAdapterAttribute)} */
	ShippoParcelAdapter create(ParcelAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
	
	
	/** {@link ParcelAdapterServiceImpl#retrieve(String)} */
	ShippoParcelAdapter retrieve(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
	
}
