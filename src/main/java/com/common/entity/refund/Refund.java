package com.common.entity.refund;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Refund extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;
	private int amount;
	private Account account;
	private RefundStatus status;
	
	
	@Column(name="AMOUNT", nullable=false, updatable=false)
	public int getAmount() {		return amount;}
	public void setAmount(int amount) {	this.amount = amount;}
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {	this.account = account;}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public RefundStatus getStatus() {	return status;}
	public void setStatus(RefundStatus status) {	this.status = status;}
	
	
	
}
