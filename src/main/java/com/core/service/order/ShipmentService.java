package com.core.service.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.Shipment;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.order.impl.ShipmentServiceImpl;

public interface ShipmentService extends GenericService<Shipment, Long>{

	//Shipment save(Address sender, Address receiver, RecommendedLabel recommendedLabel);
	
	@Deprecated void update(Shipment shipment);
	
	void flush();

	/** {@link ShipmentServiceImpl#getShipments(ShipmentStatus, DateInterval, Pageable)} */
	List<Shipment> getShipments(ShipmentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link ShipmentServiceImpl#countShipment(ShipmentStatus, DateInterval)} */
	long countShipment(ShipmentStatus status, DateInterval dateInterval);
	
	/** Get Shipment by merchant on a shipment_id 
	 * {@link ShipmentServiceImpl#getByMerchant(Long, Merchant)} */
	Shipment getByMerchant(Long shipmentId, Merchant merchant);
	
	/** {@link ShipmentServiceImpl#markAsShipped(Shipment)} */
	void markAsShipped(Shipment shipment);
	
	/** {@link ShipmentServiceImpl#updateStatus(Shipment, ShipmentStatus)} */
	@Deprecated void updateStatus(Shipment shipment, ShipmentStatus status);
	
	
	
	
}
