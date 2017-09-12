package com.core.service.shippo.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.adapter.shippo.ParcelAdapterAttribute;
import com.common.adapter.shippo.util.ShippoRequestManager;
import com.core.service.shippo.adapter.ParcelAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Parcel;

@Service("shippoParcelAdapterServiceImpl")
public class ParcelAdapterServiceImpl implements ParcelAdapterService {


	
	
	
/**
 * Create an in-app parcel_adapter
 * 
 * {@link ParcelAdapterService#create(ParcelAdapterAttribute)}
 */
	@Override
	public ShippoParcelAdapter create(ParcelAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Parcel parcel = Parcel.create(adapterAttribute.getParcelData()
									, ShippoRequestManager.getOptions());
		return ShippoParcelAdapter.create(parcel);
	}

	
	
	
/**
 * {@link ParcelAdapterService#retrieve(String)}
 */
	@Override
	public ShippoParcelAdapter retrieve(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Parcel parcel = Parcel.retrieve(tokenId, ShippoRequestManager.getOptions());
		return ShippoParcelAdapter.create(parcel);
	}

}
