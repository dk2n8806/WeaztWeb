package com.common.entity.stripe;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.adapter.stripe.CustomerAdapter;
import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.util.EqualsUtil;
import com.common.util.HashCodeUtil;


/**
 * <h1>Stripe Customer</h1>
 * 
 * <p>Or Stripe-Standalone-Account</p>
 * 
 * @author dk
 *
 */
@Entity
@Table(name="STRIPE_CUSTOMER")
public class StripeCustomer extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Account account;
	private StripeStatus status;
	private CustomerAdapter customerAdapter;
	

	
	
	public static StripeCustomer create(
			Account customer, CustomerAdapter customerAdapter) 
	{
		try {
			if(customer == null)
				throw new IllegalArgumentException("A customer account is required");
			if(customerAdapter == null)
				throw new IllegalArgumentException("Stripe customer_adapter is required");
			
			StripeCustomer c = new StripeCustomer();
			c.setAccount(customer);
			c.setCustomerAdapter(customerAdapter);
			c.setStatus(StripeStatus.ACTIVE);
			return c;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\taccount: " + account.getId() 
													+ "\n\tstatus: " + status 
													+ "\n\tcustomerAdapter: " + customerAdapter + "\n}";
	}










	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	public Account getAccount() {		return account;	}
	public void setAccount(Account customer) {	this.account = customer;}
	

	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public StripeStatus getStatus() {return status;	}
	public void setStatus(StripeStatus status) {	this.status = status;}

	
	@Embedded
	public CustomerAdapter getCustomerAdapter() {		return customerAdapter;	}
	public void setCustomerAdapter(CustomerAdapter customerAdapter) {	this.customerAdapter = customerAdapter;}
	
	

	
/***********************************************************************************
 * 
 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) return true;
		if(!(obj instanceof StripeCustomer)) return false;
		StripeCustomer other = (StripeCustomer) obj;
		return EqualsUtil.areEqual(this.getCreatedOn(), other.getCreatedOn())
				&& EqualsUtil.areEqual(this.getStatus(), other.getStatus())
				;		
	}
	
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.getCreatedOn());
		result = HashCodeUtil.hash(result, this.status);
		return super.hashCode();
	}

}
