package com.common.entity.support;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.util.EqualsUtil;
import com.common.util.HashCodeUtil;

@Entity
@Table(name="CONTACT_SUPPORT_MESSAGE")
public class ContactSupportMessage extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Account account;
	private String subject;
	private String message;
	private SupportCategory category;
	private boolean isRead;
	
	public static ContactSupportMessage create(Account account
			, String subject, SupportCategory category, String message) {
		try{	
			if(account == null) 
				throw new IllegalArgumentException("Account cannot be null");
			
			if(subject == null) 
				throw new IllegalArgumentException("Name cannot be null");
			
			if(category == null)
				throw new IllegalArgumentException("Subject category cannot be null");
			
			ContactSupportMessage contact = new ContactSupportMessage();
			contact.account = account;
			contact.subject = subject;
			contact.category = category;
			contact.message = message;
			contact.isRead = false;
			return contact;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {		return account;	}
	public void setAccount(Account account) {	this.account = account;}
	
	@Type(type="yes_no")
	@Column(name="IS_VIEWED")
	public boolean isRead() {		return isRead;	}
	public void setRead(boolean isRead) {	this.isRead = isRead;}
	
	@Column(name="SUBSJECT", nullable=false, updatable=false)
	public String getSubject() {	return subject;	}
	public void setSubject(String subject) {		this.subject = subject;}
	
	@Lob
	@Column(name="MESSAGE", nullable=false, updatable=false)
	public String getMessage() {return message;}
	public void setMessage(String message) {		this.message = message;}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY", nullable=false)
	public SupportCategory getCategory() {		return category;	}
	public void setCategory(SupportCategory category) {	this.category = category;}
	
	
	


	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(!(obj instanceof ContactSupportMessage)) return false;
		ContactSupportMessage other = (ContactSupportMessage) obj;
		return EqualsUtil.areEqual(this.subject, other.subject)
				&& EqualsUtil.areEqual(this.category, other.category)
				&& EqualsUtil.areEqual(this.message, other.message)
				&& EqualsUtil.areEqual(this.isRead, other.isRead);
	}
	
	
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.subject);
		result = HashCodeUtil.hash(result, this.category);
		result = HashCodeUtil.hash(result, this.message);
		result = HashCodeUtil.hash(result, this.isRead);
		return result;
	}
	
	
}
