package com.common.entity.payout;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.type.PayoutStatus;

@Entity
@Table(name="PAYOUT_SALE")
public class SalePayout extends Payout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderBundle orderBundle;
	
	public static SalePayout create(Merchant merchant, OrderBundle orderBundle) {
		try {
			if(merchant == null)
				throw new IllegalArgumentException("merchant entity is required");
			if(orderBundle == null)
				throw new IllegalArgumentException("Order_bundle is required");
			if(orderBundle.getCollectedAmount() <= 0)
				throw new IllegalArgumentException("amount must be > 0");
			
			SalePayout payout = new SalePayout();
			payout.setMerchant(merchant);
			payout.setStatus(PayoutStatus.PENDING);
			payout.setAmount(orderBundle.getCollectedAmount());
			payout.orderBundle = orderBundle;
			return payout;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	@ManyToOne
	@JoinColumn(name="ORDER_BUNDLE_ID", nullable=false, updatable=false)
	public OrderBundle getOrderBundle() {return orderBundle;}
	public void setOrderBundle(OrderBundle orderBundle) {	this.orderBundle = orderBundle;}
	
	
}
