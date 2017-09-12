package com.core.service.stripe.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.stripe.ChargeAdapter;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.core.dao.stripe.StripeChargeDao;
import com.core.service.stripe.StripeChargeService;


/*********************************************************************
 * 
 * 
 */
@Service
@Transactional
public class StripeChargeServiceImpl implements StripeChargeService{

	@Autowired private StripeChargeDao stripeDao;
	
	
	@Override
	public StripeCharge findById(Long id) {
		return stripeDao.findById(id);
	}

	

	@Override
	public long getRowCount() {
		return stripeDao.getRowCount();
	}

	@Override
	public StripeCharge getReference(Long id) {
		return stripeDao.getReference(id);
	}
	
	

	
	
/*********************************************************************
 * Lookup a stripe_charge by a given token id
 * 
 * {@link StripeChargeService#getByToken(String)}
 */
	@Override
	public StripeCharge getByToken(String tokenId) {
		try {
			return stripeDao.getByToken(tokenId);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
/***********************************************************************
 * Retrieve a stripe_charge by a account on a chargeId
 * 
 * {@link StripeChargeService#getByStripeCustomer(StripeCustomer, Long)}
 */
	@Override
	public StripeCharge getByStripeCustomer(StripeCustomer customer, Long chargeId) {
		if(customer == null || chargeId == null) {
			return null;
		}
		try {
			return stripeDao.getByStripeCustomer(customer, chargeId);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
/***********************************************************************
 * Retrieve a stripe_charge by an account on a chargeId
 * 
 * {@link StripeChargeService#getByAccount(Account, Long)}
 */
	@Override
	public StripeCharge getByAccount(Account account, Long chargeId) {
		if(account == null || chargeId == null) {
			return null;
		}
		try {
			return stripeDao.getByAccount(account, chargeId);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
/***********************************************************************
 * Retrieve a list of stripe_charge by a stripe_customer
 * 
 * {@link StripeChargeService#getListByStripeCustomer(StripeCustomer, Pageable)}
 */
	@Override
	public List<StripeCharge> getListByStripeCustomer(
			StripeCustomer stripeCustomer, Pageable pageable) {
		if(stripeCustomer == null) {
			return new ArrayList<StripeCharge>();
		}
		return stripeDao.getListByStripeCustomer(stripeCustomer, pageable);
	}

	
	
/***********************************************************************
 * Retrieve a stripe_charge by an account
 * 
 * {@link StripeChargeService#getListByAccount(Account, Pageable)}
 */
	@Override
	public List<StripeCharge> getListByAccount(Account account,
			Pageable pageable) {
		if(account == null) {
			return new ArrayList<StripeCharge>();
		}
		return stripeDao.getListByAccount(account, pageable);
	}

	
	
/***********************************************************************
 * Retrieve a list of stripe_charge
 * 
 * {@link StripeChargeService#getCharges(Pageable)}
 */
	@Override
	public List<StripeCharge> getCharges(Pageable pageable) {
		return stripeDao.getCharges(pageable);
	}	


	
	
/***********************************************************************
 * Count totalCharge stripe_charge by a stripe_customer
 * 
 * {@link StripeChargeService#countByStripeCustomer(StripeCustomer)}
 */
	@Override
	public long countByStripeCustomer(StripeCustomer stripeCustomer) {
		if(stripeCustomer == null) {
			return 0;
		}
		try {
			return stripeDao.countByStripeCustomer(stripeCustomer);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
/***********************************************************************
 * Count totalCharge stripe_charge by an account
 * 
 * {@link StripeChargeService#countByAccount(Account)}
 */
	@Override
	public long countByAccount(Account account) {
		if(account == null) {
			return 0;
		}
		try {
			return stripeDao.countByAccount(account);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	
	
/*****************************************************************************
 * Create stripe_charge ona stripe_customer
 * 
 * {@link StripeChargeService#create(StripeCustomer, ChargeAdapter)}
 */
	@Override
	public StripeCharge create(StripeCustomer stripeCustomer, ChargeAdapter chargeAdapter) {
		StripeCharge stripeCharge = StripeCharge.create(stripeCustomer, chargeAdapter);
		stripeDao.persist(stripeCharge);
		return stripeCharge;
	}
	
	
}
