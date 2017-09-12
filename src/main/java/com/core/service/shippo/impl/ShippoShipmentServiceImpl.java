package com.core.service.shippo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.adapter.shippo.LabelAdapter;
import com.common.adapter.shippo.LabelAdapterAttribute;
import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.adapter.shippo.RateAdapter;
import com.common.adapter.shippo.ShipmentAdapter;
import com.common.adapter.shippo.ShipmentAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.entity.address.Address;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.core.service.shippo.ShippoShipmentService;
import com.core.service.shippo.adapter.LabelAdapterService;
import com.core.service.shippo.adapter.ShipmentAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

@Service
public class ShippoShipmentServiceImpl 
	implements ShippoShipmentService 
{

	
	private final static Logger logger = LogManager.getLogger();
	
	@Autowired private ShipmentAdapterService shipmentAdapterService;
	@Autowired private LabelAdapterService labelAdapterService;
	

	@Override
	public LabelAdapter getShippingLabel(RateAdapter rateAdapter) {
		try {
			if(rateAdapter != null) {
				return labelAdapterService.create(
									new LabelAdapterAttribute(
											rateAdapter.getTokenId()));
			} else {
				logger.error("Unable to generate rate_adapter");
			}
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			logger.error("Unable to generate lable_adapter");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
/**
 * Create a new shippo label_adapter 
 * 
 * {@link ShippoShipmentService#create(String, String, String)}
 */
	@Override
	public ShipmentAdapter create(String senderTokenId, String receiverTokenId,
			String parcelTokenId) {
		try {
			ShipmentAdapter shipmentAdapter = 
					shipmentAdapterService.create(
							new ShipmentAdapterAttribute(
									senderTokenId, receiverTokenId, parcelTokenId));
			return shipmentAdapter;
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			logger.error("Unable to generate lable_adapter");
			e.printStackTrace();
		}	
		return null;
	}



	@Override
	public ShipmentAdapter create(Address sender, Address receiver, ParcelAdapter parcel) {
		try {
			return create(((ShippoAddressAdapter) sender).getToken()
							, ((ShippoAddressAdapter) receiver).getToken()
							, ((ShippoParcelAdapter) parcel).getToken());
		} catch(IllegalArgumentException e)  {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ShipmentAdapter create(Address sender, Address receiver, Parcel parcel) {
		return create(sender, receiver, parcel.getParcelAdapter());
	}

}
