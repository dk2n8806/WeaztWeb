package com.core.dao.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeStatus;
import com.core.dao.generic.GenericRepository;
import com.core.dao.stripe.impl.StripeCustomerDaoImpl;

/***********************************************************************
 * 
 * @author dk
 *
 */
public interface StripeCustomerDao 
extends GenericRepository<StripeCustomer, Long>{

	/** {@link StripeCustomerDaoImpl#findByToken(String)} */
	StripeCustomer findByToken(String tokenId);
	
	/** {@link StripeCustomerDaoImpl#getByAccount(Long, Account)} */
	StripeCustomer getByAccount(Long stripeId, Account account);
	
	
	
	
	
	/** {@link StripeCustomerDaoImpl#getListByAccount(Account, StripeStatus, Pageable)} */
	List<StripeCustomer> getListByAccount(Account account, StripeStatus status, Pageable pageable);
	
	/** {@link StripeCustomerDaoImpl#getList(StripeStatus, Pageable)} */
	List<StripeCustomer> getList(StripeStatus status, Pageable pageable);
	
	
	
	
	
	
	/** {@link StripeCustomerDaoImpl#countByAccount(Account, StripeStatus)} */
	long countByAccount(Account account, StripeStatus status);
	
	
	/** {@link StripeCustomerDaoImpl#count(StripeStatus)} */
	long count(StripeStatus status);

	/** {@link StripeCustomerDaoImpl#updateStatus(StripeCustomer, StripeStatus)} */
	void updateStatus(StripeCustomer stripe, StripeStatus status);
	
	/** {@link StripeCustomerDaoImpl#updateStatus(List, StripeStatus)} */
	void updateStatus(List<StripeCustomer> stripes, StripeStatus status);
	
}
