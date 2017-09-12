package com.common.entity.account;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.merchant.Merchant;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.AccountUtil;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="Account")
public class Account extends AbstractPersistenceObject
implements UserDetails, CredentialsContainer, Cloneable {

	private static final long serialVersionUID = 5055780751875589105L;

									
	private String username;
	private String email;
	private byte[] hashedPassword;
	private Avatar avatar;
	private Role role;
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(); 
	private boolean hasVerified = false;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	private AccountStatus status;
	private Merchant merchant;
	private Profile profile;
	
	
	
	public Account() {}
	public Account(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	/** :::
	 * <p>Create account entity instance with basic data provided by the client</p>
	 * 
	 * @param username	name of the client
	 * @param email	credential login of the client
	 * @param hashedPassword	credential protection of the client
	 * @return account entity
	 * ::: */
	public static Account createEntityInstance(String username, String email, byte[] hashedPassword) 
	{
		try {
			if(!AccountUtil.isUsernameFormat(username)) {
				throw new IllegalArgumentException("Invalid account username format");
			}
			if(!AccountUtil.isEmailFormat(email)) {
				throw new IllegalArgumentException("Invalid account email format");
			}
			if(hashedPassword == null || hashedPassword.length <= 0) {
				throw new IllegalArgumentException("Invalid password");
			}
			Account account = new Account();
			account.username 				= username;
			account.email 						= email.toLowerCase();					// Synchronize email format
			account.hashedPassword 	= hashedPassword;
			account.avatar 					=  Avatar.createEntityInstance(AccountUtil.DEFAULT_IMG);
			account.status 					= AccountStatus.ACTIVE;
			account.role 						= Role.CUSTOMER;
			account.profile 					= new Profile();		
			return account;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/***************************************************************/
	/************************ Mapping ******************************/
	/***************************************************************/

	
	@Transient
	public Merchant getMerchant() {return merchant;}
	public void setMerchant(Merchant merchant) {this.merchant = merchant;}
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="AVATAR_ID")
	public Avatar getAvatar() {return avatar;}
	public void setAvatar(Avatar avatar) {	this.avatar = avatar;}
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	public AccountStatus getStatus() {	return status;}
	public void setStatus(AccountStatus status) {	this.status = status;}
	
	@Type(type="yes_no")
	@Column(name="HAS_VERIFIED")
	public boolean isHasVerified() {		return hasVerified;	}
	public void setHasVerified(boolean hasVerified) {	this.hasVerified = hasVerified;	}
	
	
	
	
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="PROFILE_ID", nullable=false)
	public Profile getProfile() {	return profile;}
	public void setProfile(Profile profile) {	this.profile = profile;}
	
	
	
	@Column(name="EMAIL", unique=true)
	public String getEmail() {return email;	}
	public void setEmail(String email) {this.email = email;	}

	
	
	@Override
	@Column(name="USERNAME")
	public String getUsername() {		return this.username;	}
	public void setUsername(String username) {this.username = username;	}


	@Basic(fetch=FetchType.LAZY)
	@Column(name="PASSWORD")
	public byte[] getHashedPassword() {return hashedPassword;	}
	public void setHashedPassword(byte[] hashedPassword) {this.hashedPassword = hashedPassword;	}
	
	
	
	@Override	@Transient
	public String getPassword() {
		return this.getHashedPassword() == null 
						? null : new String(this.getHashedPassword(), StandardCharsets.UTF_8);
	}
	
	

	
	
	@Enumerated(EnumType.STRING)
	@Column(name="ROLE")
	public Role getRole() {return role;	}
	public void setRole(Role role) {this.role = role;	}
	
	
	
	


	@Override
	public void eraseCredentials() {this.hashedPassword = null;}


	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities() {		return this.authorities;	}
	public void setAuthorities(Set<GrantedAuthority> authorities) {this.authorities = authorities;	}
	
	
	
	@Override
	@Type(type="yes_no")
	@Column(name="IS_ACCOUNT_NON_EXPIRED")
	public boolean isAccountNonExpired() {		return this.accountNonExpired;	}
	public void setAccountNonExpired(boolean accountNonExpired) {this.accountNonExpired = accountNonExpired;	}
	

	@Override
	@Type(type="yes_no")
	@Column(name="IS_ACCOUNT_NON_LOCKED")
	public boolean isAccountNonLocked() { return this.accountNonLocked;	}
	public void setAccountNonLocked(boolean accountNonLocked) {this.accountNonLocked = accountNonLocked;	}
	
	@Override
	@Type(type="yes_no")
	@Column(name="IS_CREDENTIALS_NON_EXPIRED")
	public boolean isCredentialsNonExpired() { return this.credentialsNonExpired;	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {this.credentialsNonExpired = credentialsNonExpired;	}
	
	
	
	@Override
	@Type(type="yes_no")
	@Column(name="IS_ENABLED")
	public boolean isEnabled() { return this.enabled;	}
	public void setEnabled(boolean enabled) {this.enabled = enabled;	}


	
	
	
	
	

}
