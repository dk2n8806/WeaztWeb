package com.core.service.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.adapter.stripe.CustomerAdapter;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeStatus;
import com.core.service.GenericService;
import com.core.service.stripe.impl.StripeCustomerServiceImpl;


/**
 * <h1>StripeCustomerService</h1>
 * 
 * <p>Interface uses to interact with stripe_customer adapter</p>
 * 
 * @author dk2n_
 *
 */
public interface StripeCustomerService extends GenericService<StripeCustomer, Long> {

	
	/** {@link StripeCustomerServiceImpl#create(Account, CustomerAdapter)} */
	StripeCustomer create(Account account, CustomerAdapter adapter);
	
	/** {@link StripeCustomerServiceImpl#getByToken(String)} */
	StripeCustomer getByToken(String tokenId);
	
	/** {@link StripeCustomerServiceImpl#getByAccount(Long, Account)} */
	StripeCustomer getByAccount(Long stripeId, Account account);
	
	/** {@link StripeCustomerServiceImpl#getDefaultByAccount(Account)} */
	StripeCustomer getDefaultByAccount(Account account);
	
	
	
	
	
	/** {@link StripeCustomerServiceImpl#setDefaultByAccount(Long, Account)} */
	void setDefaultByAccount(Long stripeId, Account account) 
											throws IllegalArgumentException;
	
	
	/** {@link StripeCustomerServiceImpl#updateStatus(StripeCustomer, StripeStatus)} */
	@Deprecated void updateStatus(StripeCustomer stripe, StripeStatus status);
	
	/** {@link StripeCustomerServiceImpl#updateStatus(List, StripeStatus)} */
	void updateStatus(List<StripeCustomer> stripes, StripeStatus status);
	
	
	/** {@link StripeCustomerServiceImpl#markAsDeactive(StripeCustomer)} */
	void markAsDeactive(StripeCustomer stripe);
	
	/** {@link StripeCustomerServiceImpl#markAsDeleted(StripeCustomer)} */
	void markAsDeleted(StripeCustomer stripe);
	
	
	
	
	/** {@link StripeCustomerServiceImpl#getByAccount(Account, StripeStatus, Pageable)} */
	List<StripeCustomer> getByAccount(Account account, StripeStatus status, Pageable pageable);
	
	/** {@link StripeCustomerServiceImpl#getList(StripeStatus, Pageable)} */
	List<StripeCustomer> getList(StripeStatus status, Pageable pageable);
	
	
	
	
	
	/** {@link StripeCustomerServiceImpl#countByAccount(Account, StripeStatus)} */
	long countByAccount(Account account, StripeStatus status);
	
	/** {@link StripeCustomerServiceImpl#count(StripeStatus)} */
	long count(StripeStatus status);
	
}
