package com.common.entity.payout;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.stripe.StripeTransfer;

@Entity
@Table(name="PAYOUT_TRANSACTION")
public class PayoutTransaction extends AbstractPersistenceObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StripeTransfer transfer;
	
	public static PayoutTransaction create(StripeTransfer transfer) {
		try {
			if(transfer == null)
				throw new IllegalArgumentException("transfer entity is required");
			
			PayoutTransaction transaction = new PayoutTransaction();
			transaction.transfer = transfer;
			return transaction;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@OneToOne
	@JoinColumn(name="TRANSAFER_ID", nullable=false, updatable=false)
	public StripeTransfer getTransfer() {return transfer;}
	public void setTransfer(StripeTransfer transfer) {	this.transfer = transfer;}
}
