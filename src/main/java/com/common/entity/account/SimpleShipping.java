package com.common.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.address.Address;

/**
 * <h1>Simple Shipping</h1>
 * 
 * <p>Account's shipping location</p>
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="SIMPLE_SHIPPING")
public class SimpleShipping extends AbstractPersistenceObject 
{

	private static final long serialVersionUID = 1L;
	private Account account;
	private Address address;
	private boolean isPrimary;
	private boolean isActive;
	
	
	
	public static SimpleShipping create(Account account, Address address) {
		try {
			if(account == null) {
				throw new IllegalArgumentException("Account entity is required");
			}
			if(address == null) {
				throw new IllegalArgumentException("Address object is required");
			}
			

			SimpleShipping simple = new SimpleShipping();
			simple.account = account;
			simple.address = address;
			simple.isActive = true;
			simple.isPrimary = false;
			
			return simple;
		} catch(IllegalArgumentException e) {
			e.printStackTrace(); 
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	









	@Override
	public String toString() {
		return getClass().getName() + " {\n\taccount: " + account.getId() + "\n\taddress: " + address.getId() + "\n\tisPrimary: "
				+ isPrimary + "\n\tisActive: " + isActive + "\n\ttoString: " + super.toString() + "\n}";
	}




	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {		this.account = account;}
	
	
	//@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID", nullable=false)
	public Address getAddress() { return this.address; }
	public void setAddress(Address address) {	this.address = address;}
	
	
	
	@Type(type="yes_no")
	@Column(name="IS_PRIMARY", nullable=false)
	public boolean isPrimary() {	return isPrimary;}
	public void setPrimary(boolean isPrimary) {		this.isPrimary = isPrimary; }
	
	
	@Type(type="yes_no")
	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {		return isActive;}
	public void setActive(boolean isActive) {		this.isActive = isActive;}
	

}
