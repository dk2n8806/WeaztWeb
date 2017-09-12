package com.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.common.entity.account.Avatar;
import com.common.type.AccountStatus;
import com.common.type.Role;

public class AccountTemplateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String email;
	private Avatar avatar;
	private Role role;
	private AccountStatus status;
	private Date createdOn;

	
	
	
	
	public AccountTemplateDTO(Long id
													, String username, String email, Avatar avatar
													, Role role, AccountStatus status,Date createdOn) 
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.avatar = avatar;
		this.role = role;
		this.status = status;
		this.createdOn = createdOn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
	
	
	
	
}
