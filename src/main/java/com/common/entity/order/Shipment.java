package com.common.entity.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.address.Address;
import com.common.type.ShipmentStatus;

@Entity
@Table(name="SHIPMENT")
public class Shipment extends AbstractPersistenceObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address sender;
	private Address receiver;
	private ShipmentStatus status;
	private RecommendedLabel recommendedShipment;
	private LabelTransaction label;
	
	
	
	public static Shipment create(
			Address sender, Address receiver, RecommendedLabel recommended) 
	{
		try {
			if(sender == null) {
				throw new IllegalArgumentException("Missing sender");
			}
			if(receiver == null) {
				throw new IllegalArgumentException("Missing receiver");
			}
			if(recommended == null) {
				throw new IllegalArgumentException("Missing label_adapter");
			}
			Shipment orderShipment = new Shipment();
			orderShipment.sender = sender;
			orderShipment.receiver = receiver;
			orderShipment.recommendedShipment = recommended;
			orderShipment.status = ShipmentStatus.PENDING;
			return orderShipment;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="RECOMMENDED_LABEL_ID")
	public RecommendedLabel getRecommendedShipment() {return recommendedShipment;}
	public void setRecommendedShipment(RecommendedLabel recommendedShipment) {	this.recommendedShipment = recommendedShipment;}
	
	
	@OneToOne
	@JoinColumn(name="LABEL_TRANSACTION_ID")
	public LabelTransaction getLabel() {return label;}
	public void setLabel(LabelTransaction label) {	this.label = label;}



	@OneToOne
	@JoinColumn(name="RECEIVER_ADDRESS_ID", nullable=false, updatable=false)
	public Address getReceiver() {		return receiver;	}
	public void setReceiver(Address receiver) {this.receiver = receiver;}


	@OneToOne
	@JoinColumn(name="SENDER_ADDRESS_ID", nullable=false, updatable=false)
	public Address getSender() {		return sender;	}
	public void setSender(Address sender) {	this.sender = sender;}

	

	@Enumerated(EnumType.STRING)
	@Column(name="SHIPMENT_STATUS", nullable=false)
	public ShipmentStatus getStatus() {		return status;	}
	public void setStatus(ShipmentStatus shipmentStatus) {	this.status = shipmentStatus;}


	
	
}
