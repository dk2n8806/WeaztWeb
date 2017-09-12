package com.common.entity.payment;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.type.PaymentStatus;

/** 
 * <h1>Subscription Payment</h1>
 * 
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="PAYMENT_SUBSCRIPTION")
public class SubscriptionPayment extends Payment {

	private static final long serialVersionUID = 1L;
	
	private Subscription subscription;
	private Account account;


	
	public static SubscriptionPayment create(Account account
																, Subscription subscription, int amountPayment) 
	{
		try {
			if(amountPayment <= 0)
				throw new IllegalArgumentException("amount must > 0");
			
			SubscriptionPayment payment = new SubscriptionPayment();
			payment.account = account;
			payment.subscription = subscription;
			payment.setRequested(false);
			payment.setAmount(amountPayment);
			payment.setStatus(PaymentStatus.PENDING);
			return payment;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false, unique=true)
	public Subscription getSubscription() {return subscription;}
	public void setSubscription(Subscription subscription) {	this.subscription = subscription;}
	


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {this.account = account;}





}
