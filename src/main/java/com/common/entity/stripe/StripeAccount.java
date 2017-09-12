package com.common.entity.stripe;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.common.adapter.stripe.AccountAdapter;
import com.common.entity.AbstractPersistenceObject;
import com.common.entity.merchant.Merchant;
import com.common.util.EqualsUtil;
import com.common.util.HashCodeUtil;

@Entity
@Table(name="STRIPE_ACCOUNT")
public class StripeAccount extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Merchant merchant;
	private StripeStatus status;
	private AccountAdapter accountAdapter;
	
	
	
	
	public StripeAccount() {}
	public static StripeAccount create(Merchant merchant, AccountAdapter adapter) {
		StripeAccount account = new StripeAccount();
		account.setMerchant(merchant);
		account.setAccountAdapter(adapter);
		account.setStatus(StripeStatus.ACTIVE);
		return account;
	}
	
	
	
	
	@Embedded
	public AccountAdapter getAccountAdapter() {		return accountAdapter;	}
	public void setAccountAdapter(AccountAdapter accountAdapter) {	this.accountAdapter = accountAdapter;}
	
	
	@ManyToOne
	@JoinColumn(name="MERCHANT_ID", nullable=false, updatable=false)
	public Merchant getMerchant() {		return merchant;}
	public void setMerchant(Merchant merchant) {		this.merchant = merchant;}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public StripeStatus getStatus() {return status;	}
	public void setStatus(StripeStatus status) {	this.status = status;}
	

	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(!(obj instanceof StripeAccount)) return false;
		StripeAccount other = (StripeAccount) obj;
		return EqualsUtil.areEqual(this.getCreatedOn(), other.getCreatedOn())
				&& EqualsUtil.areEqual(this.status, other.status)
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
