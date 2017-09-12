package com.core.service.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.common.entity.merchant.Merchant;
import com.common.exception.AccountException;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.account.impl.AccountServiceImpl;


/**
 * 
 * @author dk2n_
 *
 */
public interface AccountService extends GenericService<Account, Long>{


	/** :::
	 * Update the account entity
	 * {@link AccountServiceImpl#update(Account)}
	 * ::: */
	@Deprecated void update(Account account);
	

	/** :::
	 * @return account entity or null
	 * {@link AccountServiceImpl#findById(Long)}
	 * ::: */
	Account findById(Long accountId);
	
	
	
	/** :::
	 * @return merchant entity of the account or null
	 * {@link AccountServiceImpl#getMerchant(Account)} 
	 * ::: */
	Merchant getMerchant(Account account);


	/** ::: 
	 * @return profile entity of the account or null
	 * {@link AccountServiceImpl#getProfile(Account)} 
	 * ::: */
	Profile getProfile(Account account);
	
	
	/** ::: 
	 * @return an account entity or null
	 * {@link AccountServiceImpl#getByEmail(String)} 
	 * ::: */
	Account getByEmail(String email);

	
	/** ::: {@link AccountServiceImpl#promoteToMerchant} */
	void promoteToMerchant(Long accountId) throws AccountException;
	
	/** ::: {@link AccountServiceImpl#promoteToAdmin} */
	void promoteToAdmin(Long accountId) throws AccountException;
	
	
	/** :::
	 * Deactivate an account
	 * {@link AccountServiceImpl#deactivate(Account)}
	 * ::: */
	void deactivate(Account account);

	
	/** :::
	 * Reactivate an account
	 * {@link AccountServiceImpl#reactivate(Account)}
	 * ::: */
	void reactivate(Account account);
	
	

	
	/** :::
	 * Promote an account to merchant
	 * {@link AccountServiceImpl#setMerchantRole(Account, Role)}
	 * ::: */
	//void setMerchantRole(Account account, Role role);

	
	
	
	/** :::
	 * Promote an account to admin
	 * {@link AccountServiceImpl#setAdminRole(Account)}
	 * ::: */
	//void setAdminRole(Account account);
	
	
	
	/** :::
	 * Retrieve a list of account entities 
	 * {@link AccountServiceImpl#getAccounts(Role, AccountStatus, DateInterval, Pageable)} 
	 * ::: */
	List<Account> getAccounts(Role role, AccountStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	
	/** :::
	 * Count total account entities
	 * {@link AccountServiceImpl#countAccount(Role, AccountStatus, DateInterval)} 
	 * ::: */
	long countAccount(Role role, AccountStatus status, DateInterval dateInterval);





}
