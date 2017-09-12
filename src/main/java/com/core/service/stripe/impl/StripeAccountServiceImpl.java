package com.core.service.stripe.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.stripe.AccountAdapter;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeStatus;
import com.common.util.PageSearch;
import com.core.dao.stripe.StripeAccountDao;
import com.core.service.stripe.StripeAccountService;

/*****************************************************************************
 * 
 * @author dk
 *
 */
@Service
@Transactional
public class StripeAccountServiceImpl implements StripeAccountService {

	@Autowired private StripeAccountDao stripeDao;
	
	
	@Override
	public StripeAccount findById(Long id) {
		return stripeDao.findById(id);
	}

	
	@Override
	public StripeAccount getReference(Long id) {
		return stripeDao.getReference(id);
	}
	
	@Override
	public long getRowCount() {
		return stripeDao.getRowCount();
	}
	
	
/***************************************************************************
 * Lookup a stripe_account on a tokenId
 * 
 * {@link StripeAccountService#getByToken(String)}
 */
	@Override
	public StripeAccount getByToken(String tokenId) {
		try {
			return stripeDao.getByToken(tokenId);
		} catch(NoResultException e) {
			return null;
		}
	}


	
	
/***************************************************************************
 * {@link StripeAccountService#getList(StripeStatus, Pageable)}
 */
	@Override
	public List<StripeAccount> getList(StripeStatus status,
			Pageable pageable) {
		return stripeDao.getList(status, pageable);
	}


	
	
/***************************************************************************
 * {@link StripeAccountService#count(StripeStatus)}
 */
	@Override
	public long count(StripeStatus status) {
		try {
			return stripeDao.count(status);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	
	
/****************************************************************************
 * Create a new stripe_merchant account for the account
 * 
 * {@link StripeAccountService#create(Merchant, AccountAdapter)}
 */
	@Override
	public StripeAccount create(Merchant merchant, AccountAdapter adapter) {
		StripeAccount stripe = StripeAccount.create(merchant, adapter);
		stripeDao.persist(stripe);
		return stripe;
	}
	
	
	
	
	
/****************************************************************************
 * Lookup a default stripe_account by a given merchant
 * 
 * {@link StripeAccountService#getDefaultByMerchant(Merchant)}
 */
	@Override
	public StripeAccount getDefaultByMerchant(Merchant merchant) {
		List<StripeAccount> list = this.getByMerchant(merchant, StripeStatus.DEFAULT, new PageSearch(0, 1));
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	
	
	
	
	
/****************************************************************************
 * Retrieve a list of stripe_account on a merchant
 * 
 * {@link StripeAccountService#getByMerchant(Merchant, StripeStatus, Pageable)}
 */
	@Override
	public List<StripeAccount> getByMerchant(Merchant merchant,
			StripeStatus status, Pageable pageable) {
		if(merchant == null) {
			return new ArrayList<StripeAccount>();
		}
		return stripeDao.getListByMerchant(merchant, status, pageable);
	}

	
	
	
	
	
/****************************************************************************
 * Count totalCharge stripe_account by a merchant
 * 
 * {@link StripeAccountService#countByMerchant(Merchant, StripeStatus)}
 */
	@Override
	public long countByMerchant(Merchant merchant, StripeStatus status) {
		if(merchant == null) {
			return 0;
		}
		try {
			return stripeDao.countByMerchant(merchant, status);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	
/***********************************************************************
 * Retrieve a stripe_account on a given stripeId by a merchant
 * 
 * {@link StripeAccountService#getByMerchant(Merchant, Long)}
 */
	@Override
	public StripeAccount getByMerchant(Merchant merchant, Long stripeId) {
		if(merchant == null) {
			return null;
		}
		try {
			return stripeDao.getByMerchant(merchant, stripeId);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
	
	
	
	
/*********************************************************
 * Update status of a stripe_account
 * 
 * {@link StripeAccountService#updateStatus(StripeAccount, StripeStatus)}
 */
	@Override
	public void updateStatus(StripeAccount stripe, StripeStatus status) {
		if(stripe != null) {
			this.updateStatus(Arrays.asList(stripe), status);
		}
	}
	
	
/*******************************************************
 * Update status of a list of stripe_account
 * 
 * {@link StripeAccountService#updateStatus(List, StripeStatus)}
 */
	@Override
	public void updateStatus(List<StripeAccount> stripes, StripeStatus status) {
		if(stripes.size() > 0) {
			stripeDao.updateStatus(stripes, status);
			for(StripeAccount stripe : stripes) {
				stripe.setStatus(status);
			}
		}
	}

	
/****************************************************
 * Set a stripe_account as Default
 * 
 * {@link StripeAccountService#markAsDefault(StripeAccount)}
 */
	@Override
	public void markAsDefault(StripeAccount stripe) {
		if(stripe.getStatus() != StripeStatus.DELETED) {
			long count = this.countByMerchant(stripe.getMerchant(), StripeStatus.ACTIVE);
			if(count > 0) {
				StripeAccount oldDefault = getDefaultByMerchant(stripe.getMerchant());
				if(oldDefault != null){
					markAsActive(oldDefault);
				}
			}
			updateStatus(stripe, StripeStatus.DEFAULT);
		}
		
	}
	
	
/****************************************************************
 * Set status of a stripe_account as DELETED
 * 
 * {@link StripeAccountService#markAsDelete(StripeAccount)}
 */
	@Override
	public void markAsDelete(StripeAccount stripe) {
		updateStatus(stripe, StripeStatus.DELETED);
	}

	
	
/****************************************
 * Set status of a stripe_account as ACTIVE
 * 
 * {@link StripeAccountService#markAsActive(StripeAccount)}
 */
	@Override
	public void markAsActive(StripeAccount stripe) {
		if(stripe.getStatus() != StripeStatus.DELETED) {
			updateStatus(stripe, StripeStatus.ACTIVE);
		}
	}



}
