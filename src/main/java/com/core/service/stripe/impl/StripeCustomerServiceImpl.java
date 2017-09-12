package com.core.service.stripe.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.stripe.CustomerAdapter;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeStatus;
import com.common.util.PageSearch;
import com.core.dao.stripe.StripeCustomerDao;
import com.core.service.stripe.StripeCustomerService;






/***************************************************************
 * 
 * @author dk
 *
 */
@Service
@Transactional
public class StripeCustomerServiceImpl
implements StripeCustomerService {

	@Autowired private StripeCustomerDao stripeDao;
	
	@Override
	public StripeCustomer findById(Long id) {
		return stripeDao.findById(id);
	}

	@Override
	public long getRowCount() {
		return stripeDao.getRowCount();
	}

	@Override
	public StripeCustomer getReference(Long id) {
		return stripeDao.getReference(id);
	}

	
/*******************************************************************
 * {@link StripeCustomerService#getByToken(String)}
 */
	@Override
	public StripeCustomer getByToken(String tokenId) {
		try {
			return stripeDao.findByToken(tokenId);
		}catch(NoResultException e) {
			return null;
		}
	}

	
	

	
	

	
/*******************************************************************
 * {@link StripeCustomerService#getList(StripeStatus, Pageable)}
 */
	@Override
	public List<StripeCustomer> getList(StripeStatus status,
			Pageable pageable) {
		return stripeDao.getList(status, pageable);
	}


	
	

	
/*******************************************************************
 * {@link StripeCustomerService#count(StripeStatus)}
 */
	@Override
	public long count(StripeStatus status) {
		return stripeDao.count(status);
	}
	
	
	
	
	
	

	
	
/**************************************************************************
 * Get default stripe_customer on an Account
 * 
 * {@link StripeCustomerService#getDefaultByAccount(Account)}
 */
	@Override
	public StripeCustomer getDefaultByAccount(Account account) {
		List<StripeCustomer> list = this.getByAccount(account, StripeStatus.DEFAULT, new PageSearch(0, 1));
		if(list.size() == 0){
			return null;
		} else {
			return list.get(0);
		}
	}
	
	


	
	
/*********************************************************************
 * Set a stripe_customer with stripeId as Default and set other 
 * stripe_customer to Active
 * 
 * {@link StripeCustomerService#setDefaultByAccount(Long, Account)}
 */
	@Override
	public void setDefaultByAccount(Long stripeId, Account account) {
		StripeCustomer old = this.getDefaultByAccount(account);
		StripeCustomer curr = this.getByAccount(stripeId, account);
		if(curr == null) {
			throw new IllegalArgumentException("Unable to find the stripe_customer "
					+ "by given account on the stripeId");
		} 
		if(curr.getStatus().equals(StripeStatus.DEACTIVE)
				|| curr.getStatus().equals(StripeStatus.DELETED)) {
			throw new IllegalArgumentException("Unable to set the stripe_customer as default"
					+ " because the stripe_customer has been set as 'DELETED'");
		}
		if(old != null) {
			old.setStatus(StripeStatus.ACTIVE);
			stripeDao.update(old);
		}
		curr.setStatus(StripeStatus.DEFAULT);
		stripeDao.update(curr);
		
	}


	
	
/*********************************************************************
 * Retrieve a list of stripe_customer on an account
 * 
 * {@link StripeCustomerService#getByAccount(Account, StripeStatus, Pageable)}
 */
	@Override
	public List<StripeCustomer> getByAccount(Account account,
			StripeStatus status, Pageable pageable) {
		if(account == null) {
			return new ArrayList<StripeCustomer>();
		}
		return stripeDao.getListByAccount(account, status, pageable);
	}


	
	
/*********************************************************************
 * Count totalCharge stripe_customer by an account
 * 
 * {@link StripeCustomerService#countByAccount(Account, StripeStatus)}
 */
	@Override
	public long countByAccount(Account account, StripeStatus status) 
	{
		if(account == null) {
			return 0;
		}
		try {
			return stripeDao.countByAccount(account, status);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
/*********************************************************************
 * Create a new default stripe_customer for an account
 * 
 * {@link StripeCustomerService#create(Account, CustomerAdapter)}
 */
	@Override
	public StripeCustomer create(Account account,
			CustomerAdapter adapter) {
		StripeCustomer sDefault = StripeCustomer.create(account, adapter);
		/*StripeCustomer oldDefault = this.getDefault(account);
		if(oldDefault != null) {
			oldDefault.setStatus(StripeStatus.ACTIVE);
			stripeDao.update(oldDefault);
		}*/
		stripeDao.persist(sDefault);
		return sDefault;
	}
	

	
	
	
/*****************************************************************************
 * Retrieve a stripe_customer on a given id by an account
 * 
 * {@link StripeCustomerService#getByAccount(Account, Long)}
 */
	@Override
	public StripeCustomer getByAccount(Long stripeId, Account account) 
	{
		if(account == null) {
			return null;
		}
		try {
			return stripeDao.getByAccount(stripeId, account);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
/*******************************************************************
 * Update stripe_status
 * 
 * {@link StripeCustomerService#updateStatus(StripeCustomer, StripeStatus)}
 */
	@Override
	public void updateStatus(StripeCustomer stripe, StripeStatus status) {
		if(status != null && status != null) {
			stripeDao.updateStatus(stripe, status);
			stripe.setStatus(status);
		}
	}
	
	
/****************************************************
 * Chage status of stripe_customer to deactive
 * 
 * {@link StripeCustomerService#markAsDeactive(StripeCustomer)}
 */
	@Override
	public void markAsDeactive(StripeCustomer stripe) {
		if(stripe.getStatus().equals(StripeStatus.DELETED)) {
			this.updateStatus(stripe, StripeStatus.DEACTIVE);
		}
	}
	
/**************************************************
 * Mark a stripe_custome as 'DELETED'
 * 
 * {@link StripeCustomerService#markAsDeleted(StripeCustomer)}
 */
	@Override
	public void markAsDeleted(StripeCustomer stripe) {
		this.updateStatus(stripe, StripeStatus.DELETED);
	}

	
	
/************************************************
 * Update status of stripe_customer
 * 
 * {@link StripeCustomerService#updateStatus(List, StripeStatus)}
 */
	@Override
	public void updateStatus(List<StripeCustomer> stripes, StripeStatus status) {
		if(stripes.size() > 0) {
			stripeDao.updateStatus(stripes, status);
			for(StripeCustomer stripe : stripes) {
				stripe.setStatus(status);
			}
		}
	}

}
