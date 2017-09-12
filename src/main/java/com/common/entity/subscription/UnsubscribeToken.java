package com.common.entity.subscription;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceToken;
import com.common.entity.PersistenceTokenPrefix;

@Entity
@Table(name="UNSUSBSCRIBE_TOKEN")
public class UnsubscribeToken extends AbstractPersistenceToken {

    
	private static final long serialVersionUID = 1L;
	private Subscription subscription;


	public UnsubscribeToken() {}
	private UnsubscribeToken(Subscription subscription) {
		super(PersistenceTokenPrefix.UNSUBSCRIBE_TOKEN_PREFIX);
		this.subscription = subscription;
	}
	
	public static UnsubscribeToken create(Subscription subscription) {
		return new UnsubscribeToken(subscription);
	}
	
	
	@ManyToOne
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false)
	public Subscription getSubscription() {	return subscription;}
	public void setSubscription(Subscription subscription) {		this.subscription = subscription;}

	
	
	
	
	
}
