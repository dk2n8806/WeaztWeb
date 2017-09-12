package com.core.service.shippo.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.adapter.shippo.util.ShippoRequestManager;
import com.core.service.shippo.adapter.AddressAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Address;


@Service("shippoAddressAdapterServiceImpl")
public class AddressAdapterServiceImpl implements AddressAdapterService {

	
	
	
	
/**
 * {@link AddressAdapterService#create(AddressAdapterAttribute)}
 */
	@Override
	public ShippoAddressAdapter create(AddressAdapterAttribute adapterAttribute) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, APIException 
	{
		Address address = Address.create(adapterAttribute.getAddressData()
										, ShippoRequestManager.getOptions());
		return ShippoAddressAdapter.create(address);
	}


	
	
	
/******************************************************************************
 * {@link AddressAdapterService#lookup(String)}
 */
	@Override
	public ShippoAddressAdapter lookup(String addressTokenId) 
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException {
		Address address = Address.retrieve(addressTokenId
										, ShippoRequestManager.getOptions());
		return ShippoAddressAdapter.create(address);
	}


}
