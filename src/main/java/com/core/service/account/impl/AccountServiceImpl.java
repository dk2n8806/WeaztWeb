package com.core.service.account.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.common.entity.merchant.Merchant;
import com.common.exception.AccountException;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.account.AccountDao;
import com.core.service.account.AccountService;

@Service
@Transactional
public class AccountServiceImpl  implements AccountService{

	@Autowired private AccountDao accountDao;
	
	

	/** ::::
	 * <p>Update the account entity</p>
	 * {@link AccountService#update(Account)}
	 * ::: */
	@Deprecated
	@Override
	public void update(Account account) {
		accountDao.update(account);
	}
	
	

	/** :::
	 * @return account entity or null
	 * {@link AccountService#findById(Long)}
	 * ::: */
	public Account findById(Long accountId) {
		return accountDao.findById(accountId);
	}
	
	
	/** :::
	 * <p>Retrieve a merchant entity by a given account</p>
	 * {@link AccountService#getMerchant(Account)}
	 * ::: */
	@Override
	public Merchant getMerchant(Account account) {
		try {
			return accountDao.getMerchant(account);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
	
	/** :::
	 * <p>Retrieve an account profile</p>
	 * {@link AccountService#getProfile(Account)}
	 * ::: */
	@Override
	public Profile getProfile(Account account) {
		try {
			return accountDao.getProfile(account);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
	
	

	/** :::
	 * <p>Retrieve an account by a given email</p>
	 * {@link AccountService#getByEmail(String)}
	 * ::: */
	@Override
	public Account getByEmail(String email) {
		try {
			return accountDao.getByEmail(email);
		} catch(NoResultException e) {
			return null;
		}
	}

	

	/** :::
	 * 
	 * ::: */
	@Override
	public List<Account> getAccounts(Role role, AccountStatus status, DateInterval dateInterval, Pageable pageable) {
		return accountDao.getAccounts(role, status, dateInterval, pageable);
	}

	
	
	/** :::
	 * 
	 * ::: */
	@Override
	public long countAccount(Role role, AccountStatus status, DateInterval dateInterval) {
		try {
			return accountDao.countAccount(role, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	/** :::
	 * 
	 * ::: */
	@Override
	public void deactivate(Account account) {
		accountDao.updateStatus(account, AccountStatus.DEACTIVE);
	}

	/** :::
	 * 
	 * ::: */
	@Override
	public void reactivate(Account account) {
		accountDao.updateStatus(account, AccountStatus.ACTIVE);
	}

	/** :::
	 * 
	 * ::: */
//	@Override
//	public void setMerchantRole(Account account, Role role) {
//		accountDao.updateRole(account, Role.MERCHANT);
//	}

	/** :::
	 * 
	 * ::: */
//	@Override
//	public void setAdminRole(Account account) {
//		accountDao.updateRole(account, Role.ADMIN);
//		
//	}



	@Override
	public Account getReference(Long id) {
		return accountDao.getReference(id);
	}



	@Override
	public long getRowCount() {
		return accountDao.getRowCount();
	}



	@Override
	public void promoteToMerchant(Long accountId) throws AccountException {
		Account entity = findById(accountId);
		if(entity.getStatus().equals(AccountStatus.ACTIVE) && entity.getRole().equals(Role.CUSTOMER)) {
			entity.setRole(Role.MERCHANT);
			accountDao.update(entity);
		} else {
			throw new AccountException("Invalid account stage");
		}	
	}



	@Override
	public void promoteToAdmin(Long accountId) throws AccountException {
		Account entity = findById(accountId);
		if(entity.getStatus().equals(AccountStatus.ACTIVE) && entity.getRole().equals(Role.CUSTOMER)) {
			entity.setRole(Role.ADMIN);
			accountDao.update(entity);
		} else {
			throw new AccountException("Invalid account stage");
		}
	}

}
