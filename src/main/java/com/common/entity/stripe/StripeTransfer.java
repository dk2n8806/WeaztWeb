package com.common.entity.stripe;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.common.adapter.stripe.TransferAdapter;
import com.common.entity.AbstractPersistenceObject;
import com.common.util.EqualsUtil;
import com.common.util.HashCodeUtil;


@Entity
@Table(name="STRIPE_TRANSFER")
@org.hibernate.annotations.Table(
	appliesTo = "STRIPE_TRANSFER",
	indexes= {
		@Index(name="STRIPE_ACCOUNT_TRANSFER_IDX", columnNames="STRIPE_ACCOUNT_ID")
	}
)
public class StripeTransfer extends AbstractPersistenceObject {


	private static final long serialVersionUID = 1L;
	private StripeAccount stripeAccount;
	private TransferAdapter transferAdapter;

	
	
	public static StripeTransfer create(StripeAccount stripeAccount, TransferAdapter transferAdapter) {
		StripeTransfer stripeTransfer = new StripeTransfer();
		stripeTransfer.setStripeAccount(stripeAccount);
		stripeTransfer.setTransferAdapter(transferAdapter);
		return stripeTransfer;
	}

	
	
	
	
	@ManyToOne
	@JoinColumn(name="STRIPE_ACCOUNT_ID", nullable=false, updatable=false)
	public StripeAccount getStripeAccount() {	return stripeAccount;}
	public void setStripeAccount(StripeAccount stripeAccount) {	this.stripeAccount = stripeAccount;}
	
	
	@Embedded
	public TransferAdapter getTransferAdapter() {		return transferAdapter;	}
	public void setTransferAdapter(TransferAdapter transferAdapter) {	this.transferAdapter = transferAdapter;}
	
	
	
	
	
	
/***********************************************************************************
 * 
 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) return true;
		if(!(obj instanceof StripeTransfer)) return false;
		StripeTransfer other = (StripeTransfer) obj;
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
