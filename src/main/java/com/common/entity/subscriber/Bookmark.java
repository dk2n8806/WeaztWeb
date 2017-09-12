package com.common.entity.subscriber;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.entity.product.Product;

@Entity
@Table(name="BOOKMARK")
public class Bookmark extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;

	private Product product;
	private Account account;
	private boolean isActive;
	

	public static Bookmark create(Account account, Product product) {
		try {
			
			Bookmark list = new Bookmark();
			list.account = account;
			list.product = product;
			list.isActive = true;
			return list;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Type(type="yes_no")
	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {return isActive;}
	public void setActive(boolean isActive) {	this.isActive = isActive;}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {		return account;	}
	public void setAccount(Account account) {	this.account = account;	}
	
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;	}

	
	
	
	
	
	
}
