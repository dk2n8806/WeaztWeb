package com.common.entity.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.stripe.StripeCharge;

@Entity
@Table(name="PAYMENT_TRANSACTION")
public class PaymentTransaction  extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

	private StripeCharge charge;
	
	
	public static PaymentTransaction create(StripeCharge charge) {
		try {
			if(charge == null)
				throw new IllegalArgumentException("Charge is required");
			
			PaymentTransaction transaction = new PaymentTransaction();
			transaction.charge = charge;
			return transaction;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CHARGE_ID", nullable=false, updatable=false)
	public StripeCharge getCharge() {return charge;}
	public void setCharge(StripeCharge charge) {this.charge = charge; }
}
