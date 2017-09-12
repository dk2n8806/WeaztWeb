package com.common.entity.subscription;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.type.SubscriptionStatus;

@Entity
@Table(name="UNSUBSCRIPTION")
public class Unsubscription extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Subscription subscription;
	
	
	public static Unsubscription create(Subscription subscription) {
		try {
			if(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED))
				throw new IllegalArgumentException("Invalid subscription status");
			
			Unsubscription unsubscription = new Unsubscription();
			unsubscription.subscription = subscription;
			return unsubscription;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false)
	public Subscription getSubscription() {	return subscription;}
	public void setSubscription(Subscription subscription) {	this.subscription = subscription;}
	
}
