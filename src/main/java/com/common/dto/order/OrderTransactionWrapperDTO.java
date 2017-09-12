package com.common.dto.order;

import java.io.Serializable;

import com.common.entity.order.OrderTransaction;
import com.common.entity.order.Shipment;


/**
 * 
 * @author dk2n_
 *
 */
public class OrderTransactionWrapperDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	private OrderTransaction transaction;
	private Shipment shipment;
	
	public OrderTransactionWrapperDTO(OrderTransaction transaction, Shipment shipment) {
		super();
		this.transaction = transaction;
		this.shipment = shipment;
	}
	
	public OrderTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(OrderTransaction transaction) {
		this.transaction = transaction;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	

	
	
	
}

