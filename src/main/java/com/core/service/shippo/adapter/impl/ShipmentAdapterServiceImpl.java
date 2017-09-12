package com.core.service.shippo.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.shippo.ShipmentAdapter;
import com.common.adapter.shippo.ShipmentAdapterAttribute;
import com.common.adapter.shippo.util.ShippoRequestManager;
import com.core.service.shippo.adapter.ShipmentAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Shipment;



@Service("shippoShipmentAdapterServiceImpl")
public class ShipmentAdapterServiceImpl implements ShipmentAdapterService {


	
/**
 * {@link ShipmentAdapterService#create(ShipmentAdapterAttribute)}
 */
	@Override
	public ShipmentAdapter create(
			ShipmentAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Shipment shipment = Shipment.create(adapterAttribute.getMetadata()
										, ShippoRequestManager.getOptions());
		return ShipmentAdapter.create(shipment);
	}

	
/******************************************************************************************
 * {@link ShipmentAdapterService#lookup(String)}
 */
	@Override
	public ShipmentAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Shipment shipment = Shipment.retrieve(tokenId, ShippoRequestManager.getOptions());
		return ShipmentAdapter.create(shipment);
	}

}
