package com.core.service.shippo.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.adapter.shippo.ParcelAdapterAttribute;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;
import com.core.service.shippo.ShippoParcelService;
import com.core.service.shippo.adapter.ParcelAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;


@Service
@Transactional
public class ShippoParcelServiceImpl implements ShippoParcelService {

	@Autowired private ParcelAdapterService parcelAdapterService;
	

	private static Logger logger = LogManager.getLogger();
	
	
	@Override
	public ShippoParcelAdapter create(Measurement measurement, Weight weight) {
		try {
			return parcelAdapterService.create(new ParcelAdapterAttribute(measurement, weight));
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			logger.error("Unable to create shippo_parcel_adapter");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
