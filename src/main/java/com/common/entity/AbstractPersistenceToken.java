package com.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.common.util.TokenGenerator;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractPersistenceToken 
	extends AbstractPersistenceObject 
{

	private static final long serialVersionUID = 1L;

	protected String token;
	protected boolean isUseable;
	
	
	public AbstractPersistenceToken() {}
	protected AbstractPersistenceToken(String prefix) {
		setToken(TokenGenerator.generateToken(prefix));
		setUseable(true);
		setCreatedOn(new Date());
	}
	
	
	@Transient
	public boolean getValidate() {
		return isUseable;
	}
	
	
	
	
	@Column(name="TOKEN", nullable=false)
	public String getToken() { return this.token;	}
	public void setToken(String token) { this.token = token; }

	
	
	@Type(type="yes_no")
	@Column(name="IS_USEABLE", nullable=false)
	public boolean isUseable() {		return this.isUseable;}
	public void setUseable(boolean isExpired) {	this.isUseable = isExpired;}

}
