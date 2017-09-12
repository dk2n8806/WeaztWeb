package com.common.entity.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.subscription.Subscription;

@Entity
@Table(name="ORDER_TRANSACTION")
public class OrderTransaction extends AbstractPersistenceObject {

	
	private static final long serialVersionUID = 1L;
	private OrderBundle orderBundle;
	private Subscription subscription;
	private int total;
	private Shipment shipment;
	
	
	public static OrderTransaction create(Subscription subscription, Shipment shipment) {
		try {
			if(subscription == null)
				throw new IllegalArgumentException("Subscripiton is required");
			if(shipment == null)
				throw new IllegalArgumentException("shipmnet is required");
			OrderTransaction transaction = new OrderTransaction();
			transaction.subscription = subscription;
			transaction.shipment = shipment;
			transaction.total = subscription.getPerShipment().getSubscriptionCost();
			return transaction;
		} catch(IllegalArgumentException e){
			e.printStackTrace();
			return null;
		}
	}
	
	


	@Column(name="TOTAL", nullable=false)
	public int getTotal() {		return total;} 
	public void setTotal(int total) {		this.total = total;}
	

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="SHIPMENT_ID", nullable=false, updatable=false, unique=true)
	public Shipment getShipment() {		return shipment;}
	public void setShipment(Shipment shipment) {		this.shipment = shipment;}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDER_BUNDLE_ID", nullable=false, updatable=false)
	public OrderBundle getOrderBundle() {return orderBundle;}
	public void setOrderBundle(OrderBundle orderBundle) {	this.orderBundle = orderBundle;}
	
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false)
	public Subscription getSubscription() {return subscription;}
	public void setSubscription(Subscription subscription) {	this.subscription = subscription;}
	
}
