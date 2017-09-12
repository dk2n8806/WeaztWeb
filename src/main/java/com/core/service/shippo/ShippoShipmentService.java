package com.core.service.shippo;


import com.common.adapter.shippo.LabelAdapter;
import com.common.adapter.shippo.RateAdapter;
import com.common.adapter.shippo.ShipmentAdapter;
import com.common.entity.address.Address;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.core.service.shippo.impl.ShippoShipmentServiceImpl;

public interface ShippoShipmentService {

	/** Create shipping_rate for a shipment 
	 * {@link ShippoShipmentServiceImpl#create(String, String, String)}*/
	ShipmentAdapter create(String senderTokenId, String receiverTokenId, String parcelId);
	
	/** {@link ShippoShipmentServiceImpl#create(Address, Address, ParcelAdapter)} */
	ShipmentAdapter create(Address sender, Address receiver, ParcelAdapter parcel);
	
	/** {@link ShippoShipmentServiceImpl#create(Address, Address, Parcel)} */
	ShipmentAdapter create(Address sender, Address receiver, Parcel parcel);
	
	

	/** generate shipping label by a rate adapter
	 * {@link ShippoShipmentService#getShippingLabel(RateAdapter)}*/
	LabelAdapter getShippingLabel(RateAdapter rateAdapter);
	
}
