package com.core.service.order;

import com.common.adapter.shippo.RateAdapter;
import com.common.entity.order.Shipment;
import com.core.service.order.impl.ShipmentTransactionServiceImpl;

/** :::
 * <p>A service to generate shipping label for a shipment with a given shipping rate</p>
 * 
 * @author dk2n_
 *
 */
public interface ShipmentTransactionService {

	/** Create Shipping_Label on a rate_adapter
	 * {@link ShipmentTransactionServiceImpl#generateShippingLabel(Shipment, RateAdapter)} */
	void generateShippingLabel(Shipment shipment, RateAdapter rateAdapter);
}
