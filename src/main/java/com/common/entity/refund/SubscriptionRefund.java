package com.common.entity.refund;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.calc.SubscriptionRefundCalculator;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;

/**
 * A class the hold the refund payment when cancel a subscription
 *
 */
@Entity
@Table(name="SUBSCRIPTION_REFUND")
public class SubscriptionRefund extends Refund{

	private static final long serialVersionUID = 1L;
	private Subscription subscription;
	
	public static SubscriptionRefund create(
							Account account, Subscription subscription) 
	{
		try {
			int amount = new SubscriptionRefundCalculator(subscription).getRefundAmount();
			if(amount == 0)
				throw new IllegalArgumentException("Amount must be >= 0");
			
			SubscriptionRefund refund = new SubscriptionRefund();
			refund.setAccount(account);
			refund.setStatus(RefundStatus.PENDING);
			refund.setAmount(amount);
			refund.subscription = subscription;
			return refund;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@ManyToOne
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false, unique=true)
	public Subscription getSubscription() {	return subscription;}
	public void setSubscription(Subscription subscription) {	this.subscription = subscription;}
	
	
}
