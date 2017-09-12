package com.common.entity.payout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.merchant.Merchant;
import com.common.type.PayoutStatus;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Payout extends AbstractPersistenceObject{

	
	private static final long serialVersionUID = 1L;
	private int amount;
	private PayoutStatus status;
	private Merchant merchant;
	
	
	
	
	
	
	@ManyToOne
	@JoinColumn(name="MECHANT_ID", nullable=false, updatable=false)
	public Merchant getMerchant() {	return merchant;}
	public void setMerchant(Merchant merchant) {	this.merchant = merchant;}

	@Column(name="AMOUNT", nullable=false, updatable=false)
	public int getAmount() {	return amount;}
	public void setAmount(int amount) {	this.amount = amount;}
	

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public PayoutStatus getStatus() {return status;}
	public void setStatus(PayoutStatus status) {	this.status = status;}
	

	
	
}
