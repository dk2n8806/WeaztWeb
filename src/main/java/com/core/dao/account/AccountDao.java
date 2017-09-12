package com.core.dao.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.common.entity.merchant.Merchant;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.account.impl.AccountDaoImpl;
import com.core.dao.generic.GenericRepository;

/**
 * @author dk2n_
 *
 ::: */
public interface AccountDao extends GenericRepository<Account, Long>
{

	
	/** :::
	 * Retrieve a merchant entity by an account
	 * {@link AccountDaoImpl#getMerchant(Account)} 
	 * ::: */
	Merchant getMerchant(Account customer);


	/** ::: 
	 * Retrieve a profile entity by a given account
	 * {@link AccountDaoImpl#getProfile(Account)} 
	 * ::: */
	Profile getProfile(Account customer);
	
	
	/** ::: 
	 * Retrieve an account entity by a given email
	 * {@link AccountDaoImpl#getByEmail(String)} 
	 * ::: */
	Account getByEmail(String email);

	
	
	/** :::
	 * Update an account status 
	 * {@link AccountDaoImpl#updateStatus(Account, AccountStatus)} 
	 * ::: */
	void updateStatus(Account customer, AccountStatus status);
	
	
	
	/** :::
	 * Update a new role to an account
	 * {@link AccountDaoImpl#updateRole(Account, Role)} 
	 * ::: */
	void updateRole(Account customer, Role role);
	
	
	
	/** :::
	 * Retrieve a list of account entities 
	 * {@link AccountDaoImpl#getAccounts(Role, AccountStatus, DateInterval, Pageable)} 
	 * ::: */
	List<Account> getAccounts(Role role, AccountStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	
	/** :::
	 * Count total account entities
	 * {@link AccountDaoImpl#countAccount(Role, AccountStatus, DateInterval)} 
	 * ::: */
	long countAccount(Role role, AccountStatus status, DateInterval dateInterval);
}
