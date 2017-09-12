package com.core.service.shippo;

import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;
import com.core.service.shippo.impl.ShippoParcelServiceImpl;

public interface ShippoParcelService {

	/** {@link ShippoParcelServiceImpl#create(Measurement, Weight)} */
	ShippoParcelAdapter create(Measurement measurement, Weight weight);
	
	
}
