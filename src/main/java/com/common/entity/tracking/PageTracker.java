package com.common.entity.tracking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class PageTracker extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Account account;
	private String ip;
	
	
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {	this.account = account;}
	
	@Column(name="IP")
	public String getIp() {	return ip;}
	public void setIp(String ip) {	this.ip = ip;}
	
	
	
	
}
