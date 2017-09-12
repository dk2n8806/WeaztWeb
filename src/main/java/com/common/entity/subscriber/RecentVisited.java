package com.common.entity.subscriber;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.entity.product.Product;

@Entity
@Table(name="RECENT_VISITED")
public class RecentVisited extends AbstractPersistenceObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private Product product;
	private Date lastVisited;
	private boolean isActive;
	
	
	public static RecentVisited create(Account account, Product product) {
		try {
			if(account == null)
				throw new IllegalArgumentException("account entity is required");
			if(product == null)
				throw new IllegalArgumentException("product entity is required");
			
			RecentVisited recent = new RecentVisited();
			recent.account = account;
			recent.product = product;
			recent.lastVisited = new Date();
			recent.isActive = true;
			return recent;
		}catch(IllegalArgumentException e){
			return null;
		}
	}
	
	
	@Column(name="IS_ACTIVE")
	public boolean isActive() {return isActive;}
	public void setActive(boolean isActive) {	this.isActive = isActive;}
	
	
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID", nullable=false)
	public Account getAccount() {	return account;}
	public void setAccount(Account account) {	this.account = account;}
	
	

	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false)
	public Product getProduct() {	return product;}
	public void setProduct(Product product) {	this.product = product;}

	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_VISITED")
	public Date getLastVisited() {	return lastVisited;}
	public void setLastVisited(Date lastVisited) {	this.lastVisited = lastVisited;}
	
	
}
