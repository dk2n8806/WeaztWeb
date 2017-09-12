package com.common.calc;

import com.common.entity.subscription.Subscription;

public class SubscriptionRefundCalculator {

	private Subscription subscription;
	
	public SubscriptionRefundCalculator(Subscription subscription) {
		this.subscription = subscription;
	}
	
	public int getRefundAmount() {
		return getAvgCharge() * getRemainShipment();
	}
	
	/**
	 * <p></p>
	 */
	public int getRemainShipment() {
		return subscription.getNos() - subscription.getHadShipped();
	}
	
	public int getAvgCharge() {
		return subscription.getTotalCharge() / subscription.getNos();
	}
	
	
	
}
