package com.core.service.shippo.adapter;

import com.common.adapter.shippo.ShipmentAdapter;
import com.common.adapter.shippo.ShipmentAdapterAttribute;
import com.core.service.shippo.adapter.impl.ShipmentAdapterServiceImpl;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

public interface ShipmentAdapterService {

	/** 
	 * {@link ShipmentAdapterServiceImpl#create(ShipmentAdapterAttribute)} 
	 * */
	ShipmentAdapter create(ShipmentAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
	
	
	/** 
	 * {@link ShipmentAdapterServiceImpl#lookup(String)} 
	 * */
	ShipmentAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
}
