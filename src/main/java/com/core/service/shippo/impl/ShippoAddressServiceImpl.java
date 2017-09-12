package com.core.service.shippo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.core.service.shippo.ShippoAddressService;
import com.core.service.shippo.adapter.AddressAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

/** 
 * 
 * 
 * @author dk2n_
 *
 */
@Service
public class ShippoAddressServiceImpl implements ShippoAddressService 
{

	@Autowired private AddressAdapterService adapterService;
	
	
		
	/** :::
	 * <p>Create a new shippo address_adapter</p>
	 * 
	 * {@link ShippoAddressService#create(AddressAdapterAttribute)}
	 * ::: */
	@Override
	public ShippoAddressAdapter create(AddressAdapterAttribute attribute) {
		try {
			return adapterService.create(attribute);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			e.printStackTrace();
			return null;
		}
		
	}



}
