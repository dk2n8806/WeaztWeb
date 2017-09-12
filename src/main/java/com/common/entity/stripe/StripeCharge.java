package com.common.entity.stripe;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.common.adapter.stripe.ChargeAdapter;
import com.common.entity.AbstractPersistenceObject;
import com.common.util.EqualsUtil;
import com.common.util.HashCodeUtil;

@Entity
@Table(name="STRIPE_CHARGE")
@org.hibernate.annotations.Table(
	appliesTo = "STRIPE_CHARGE",
	indexes= {
		@Index(name="STRIPE_CUSTOMER_CHARGE_IDX", columnNames="STRIPE_CUSTOMER_ID")
	}
)
public class StripeCharge extends AbstractPersistenceObject {


	private static final long serialVersionUID = 1L;
	private ChargeAdapter chargeAdapter;
	private StripeCustomer stripeCustomer;
	

	public static StripeCharge create(
			StripeCustomer stripeCustomer, ChargeAdapter chargeAdapter) {
		try {
			if(stripeCustomer == null)
				throw new IllegalArgumentException("stripe_customer is required");
			if(chargeAdapter == null)
				throw new IllegalArgumentException("charge is required");
			StripeCharge stripeCharge = new StripeCharge();
			stripeCharge.setChargeAdapter(chargeAdapter);
			stripeCharge.setStripeCustomer(stripeCustomer);
			return stripeCharge;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	
	@ManyToOne
	@JoinColumn(name="STRIPE_CUSTOMER_ID", nullable=false, updatable=false)
	public StripeCustomer getStripeCustomer() {		return stripeCustomer;	}
	public void setStripeCustomer(StripeCustomer stripeCustomer) {	this.stripeCustomer = stripeCustomer;}
	
	
	@Embedded
	public ChargeAdapter getChargeAdapter() {		return chargeAdapter;	}
	public void setChargeAdapter(ChargeAdapter chargeAdapter) {	this.chargeAdapter = chargeAdapter;}
	
	
	

	

/***********************************************************************************
 * 
 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) return true;
		if(!(obj instanceof StripeCharge)) return false;
		StripeCharge other = (StripeCharge) obj;
		return EqualsUtil.areEqual(this.getCreatedOn(), other.getCreatedOn())
				;		
	}
	
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.getCreatedOn());
		return super.hashCode();
	}

}
