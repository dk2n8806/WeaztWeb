package com.common.entity.subscription;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.NoShipmentRule;
import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.embeded.SubscriptionRating;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.exception.SubscriptionException;
import com.common.type.AccountStatus;
import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;

@Entity
@Table(name="SUBSCRIPTION")
public class Subscription extends AbstractPersistenceObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int nos;
	private int frequency;
	private int totalCharge;
	private int hadShipped;

	private Product product;
	private Account account;
	private Date scheduledOn;
	private SubscriptionStatus status;
	private ValuePerShipment perShipment;
	private SubscriptionRating rating;
	
	
	public static Subscription create(
			Account account, Product product, ValuePerShipment perShipment, int nos, int frequency) {
		
		try {
			if(!NoShipmentRule.isValidated(nos)) 
				throw new IllegalArgumentException("Invalid nos");
			if(frequency < 7) 
				throw new IllegalArgumentException("frequency must be >= 7");
			if(account.getStatus().equals(AccountStatus.DEACTIVE)) 
				throw new IllegalArgumentException("Invalid account state");
			if(!product.getStatus().equals(ProductStatus.PUBLIC))
				throw new IllegalArgumentException("Invalid product state");
			
			Subscription subscription = new Subscription();
			subscription.account = account;
			subscription.product = product;
			subscription.nos = nos;
			subscription.frequency = frequency;
			subscription.perShipment = perShipment;
			subscription.totalCharge = perShipment.totalCharge() * nos;
			subscription.hadShipped = 0;
			subscription.status = SubscriptionStatus.SUBSCRIBING;
			subscription.scheduledOn = new Date();
			subscription.rating = SubscriptionRating.create();
			return subscription;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	

	
@Override
	public String toString() {
		return getClass().getName() + " {\n\tproduct: " + product.getId() 
				+ "\n\taccount: " + account.getId() + "\n\tnos: " + nos
				+ "\n\tfrequency: " + frequency + "\n\tperShipment: " + perShipment + "\n\ttotalCharge: " + totalCharge
				+ "\n\thadShipped: " + hadShipped + "\n\tstatus: " + status + "\n\tscheduledOn: " + scheduledOn + "\n}";
	}






/** :::
 * <h1>Next subsription</h1>
 * <p>Self-auto update next value of the subscription</p>
 * 
 * @precondition 
 * - Subscription status must be SUBSCRIBED or SUBSCRIBINNG
 * - Number of received must be <= (number_of_shipment - 1)
 * ::: */
	public void nextSubscription() throws SubscriptionException {
		if(this.getStatus() == SubscriptionStatus.UNSUBSCRIBED) 
			throw new SubscriptionException("Invalid stusbscription state'");
		if(this.hadShipped > this.nos - 1) 
			throw new SubscriptionException("Number of had_used must be <= max_uses");
		
		
		this.hadShipped += 1;
		if(this.hadShipped == this.nos) {
			this.status = SubscriptionStatus.UNSUBSCRIBED;
			this.scheduledOn = null; 	// Prevent accidental generate new subscription
		} 
		else {
			this.status = SubscriptionStatus.SUBSCRIBED;
			this.scheduledOn = getNextShcedule(this.scheduledOn, this.frequency);
		}
	}
	

	private Date getNextShcedule(Date date, int next) {
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(date, next);
		return dateInterval.getTo();
	}

	
	
	@Embedded
	public SubscriptionRating getRating() {	return rating;}
	public void setRating(SubscriptionRating rating) {	this.rating = rating;}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {		return account;	}
	public void setAccount(Account account) {	this.account = account;	}
	
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;	}


	@Column(name="NOS")
	public int getNos() {	return nos;}
	public void setNos(int nos) {this.nos = nos;}


	@Column(name="FREQUENCY", nullable=false)
	public int getFrequency() {	return frequency;}
	public void setFrequency(int frequency) {this.frequency = frequency;}


	@Embedded
	public ValuePerShipment getPerShipment() {	return perShipment;}
	public void setPerShipment(ValuePerShipment perShipment) {this.perShipment = perShipment;}


	@Column(name="TOTAL_CHARGE", nullable=false)
	public int getTotalCharge() {	return totalCharge;}
	public void setTotalCharge(int totalCharge) {this.totalCharge = totalCharge;}


	@Column(name="HAD_SHIPPED", nullable=false)
	public int getHadShipped() {	return hadShipped;}
	public void setHadShipped(int hadShipped) {this.hadShipped = hadShipped;}


	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public SubscriptionStatus getStatus() {	return status;}
	public void setStatus(SubscriptionStatus status) {	this.status = status;}
	
	@Column(name="SCHEDULED_ON", nullable=false)
	public Date getScheduledOn() {	return scheduledOn;}
	public void setScheduledOn(Date scheduledOn) {this.scheduledOn = scheduledOn;}
	
	
	
	
	
	
	
	
	
	
	

}
