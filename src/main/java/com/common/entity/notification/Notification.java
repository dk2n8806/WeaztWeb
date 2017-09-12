package com.common.entity.notification;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Notification extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Account account;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {	this.account = account;}
	
}
