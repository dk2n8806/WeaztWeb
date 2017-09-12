package com.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.shippo.LabelAdapter;
import com.common.adapter.shippo.RateAdapter;
import com.common.entity.order.GeneratedLabel;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.Shipment;
import com.common.type.OrderStatus;
import com.common.type.ShipmentStatus;
import com.core.service.order.GeneratedLabelService;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderTransactionService;
import com.core.service.order.ShipmentService;
import com.core.service.order.ShipmentTransactionService;
import com.core.service.shippo.ShippoShipmentService;

@Service
@Transactional
public class ShipmentTransactionServiceImpl 
	implements ShipmentTransactionService
{
	
	@Autowired private ShipmentService shipmentService;
	@Autowired private ShippoShipmentService shippoService;
	@Autowired private OrderBundleService orderService;
	@Autowired private OrderTransactionService orderTransactionService;

	@Autowired private GeneratedLabelService generatedLabelService;
	
/**
 * Generate Shipping label for a shipment.
 * While generate shipping label for a shipment, determine if the 
 */
	@Override
	public void generateShippingLabel(Shipment shipment, RateAdapter rateAdapter) {
		LabelAdapter labelAdapter = shippoService.getShippingLabel(rateAdapter);
		GeneratedLabel label = generatedLabelService.save(labelAdapter);
		shipment.setLabel(label);
		
		if(shipment.getStatus().equals(ShipmentStatus.PENDING)) {
			shipment.setStatus(ShipmentStatus.SHIPPED);
			
			//###
			// Lookup order_transaction info of a shipment
			// Calculate Shipping label fees on a order
			//###
			OrderTransaction orderTransaction = 
					orderTransactionService.getByShipment(shipment);
			OrderBundle order = orderService.findById(orderTransaction.getOrderBundle().getId());
			order.setServiceFee(order.getServiceFee() + label.getLabelAdapter().getShippingCost());
			long count = orderTransactionService.countByOrder(order, ShipmentStatus.PENDING);
			if(count == 0) {
				order.setStatus(OrderStatus.COMPLETED);
			}	
			orderService.update(order);
		}
		shipmentService.update(shipment);		
	}
}
