package com.common.entity.account;



import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.springframework.security.core.GrantedAuthority;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.merchant.Merchant;
import com.common.type.AccountStatus;
import com.common.type.Role;


@StaticMetamodel(Account.class)
public class Account_ extends AbstractPersistenceObject_ 
{
	
	public static volatile SingularAttribute<Account, String> 					username;
	public static volatile SingularAttribute<Account, Avatar> 					avatar;
	public static volatile SingularAttribute<Account, String> 					email;
	public static volatile SingularAttribute<Account, Role> 						role; 
	public static volatile SingularAttribute<Account, Boolean> 				hasVerified;
	public static volatile 	    SetAttribute<Account, GrantedAuthority> 	authorities;
	public static volatile SingularAttribute<Account, Boolean> 				accountNonExpired;
	public static volatile SingularAttribute<Account, Boolean> 				accountNonLocked;
	public static volatile SingularAttribute<Account, Boolean> 				credentialsNonExpired;
	public static volatile SingularAttribute<Account, Boolean> 				enabled;
	public static volatile SingularAttribute<Account, Merchant> 				merchant;
	public static volatile SingularAttribute<Account, Profile> 					profile;
	public static volatile SingularAttribute<Account, AccountStatus> 		status;
	
	
}
