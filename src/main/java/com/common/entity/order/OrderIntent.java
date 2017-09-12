package com.common.entity.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.type.SubscriptionStatus;


@Entity
@Table(name="ORDER_INTENT")
public class OrderIntent extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;

	private Subscription subscription;
	private Date scheduledDate;
	private OrderIntentStatus status;
	
	
	public static OrderIntent create( Subscription subscription, Date scheduledDate) {
		try {
			if(subscription == null || subscription.getId() == null) 
				throw new IllegalArgumentException("Subscription entity is required");
			if(subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)) 
				throw new IllegalArgumentException("Invalid subscription state");
			
			
			OrderIntent order = new OrderIntent();
			order.subscription = subscription;
			order.scheduledDate = scheduledDate;
			order.status = OrderIntentStatus.REQUESTING;
			return order;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	


	@ManyToOne
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false)
	public Subscription getSubscription() {	return subscription;}
	public void setSubscription(Subscription subscription) {this.subscription = subscription;}


	@Column(name="SCHEDULED_DATE", nullable=false, updatable=false)
	public Date getScheduledDate() {	return scheduledDate;}
	public void setScheduledDate(Date scheduledOn) {	this.scheduledDate = scheduledOn;}


	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public OrderIntentStatus getStatus() {	return status;}
	public void setStatus(OrderIntentStatus status) {this.status = status;}
	
	
	
	
	
	
	
	
	
	

}
