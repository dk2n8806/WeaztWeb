package com.common.entity.payment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.common.entity.AbstractPersistenceObject;
import com.common.type.PaymentStatus;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Payment extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;
	private int amount;
	private PaymentStatus status;
	private PaymentTransaction transaction;
	private boolean isRequested;


	
	
	@Column(name="IS_REQUESTED")
	public boolean isRequested() {return isRequested;}
	public void setRequested(boolean isRequested) {	this.isRequested = isRequested;}
	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="TRANSACTION_ID")
	public PaymentTransaction getTransaction() {	return transaction;}
	public void setTransaction(PaymentTransaction transaction) {	this.transaction = transaction;}
	
	@Column(name="AMOUNT", nullable=false, updatable=false)
	public int getAmount() {return amount;}
	public void setAmount(int amount) {	this.amount = amount;}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public PaymentStatus getStatus() {return status;}
	public void setStatus(PaymentStatus status) {	this.status = status;}
	
	
	
}
