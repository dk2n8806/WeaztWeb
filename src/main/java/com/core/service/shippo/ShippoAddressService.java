package com.core.service.shippo;

import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.core.service.shippo.impl.ShippoAddressServiceImpl;


/**
 * <h1>ShippoAddressService</h1>
 * 
 * <p>A service is used to create an adapter to shipo_address.</p>
 * 
 * @author dk2n_
 *
 */
public interface ShippoAddressService {

	/** {@link ShippoAddressServiceImpl#create(AddressAdapterAttribute)} */
	ShippoAddressAdapter create(AddressAdapterAttribute attribute);
	
	
}
