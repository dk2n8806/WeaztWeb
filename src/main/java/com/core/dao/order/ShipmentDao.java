package com.core.dao.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.Shipment;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.ShipmentDaoImpl;

public interface ShipmentDao 
extends GenericRepository<Shipment, Long>{

	void flush();

	/** {@link ShipmentDaoImpl#getShipments(ShipmentStatus, DateInterval, Pageable)} */
	List<Shipment> getShipments(ShipmentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link ShipmentDaoImpl#countShipment(ShipmentStatus, DateInterval)} */
	long countShipment(ShipmentStatus status, DateInterval dateInterval);
	
	
	/** {@link ShipmentDaoImpl#updateStatus(Shipment, ShipmentStatus)} */
	void updateStatus(Shipment shipment, ShipmentStatus status);
	
	/** {@link ShipmentDaoImpl#getByMerchant(Long, Merchant)} */
	Shipment getByMerchant(Long shipmentId, Merchant merchant);
	
	
}
