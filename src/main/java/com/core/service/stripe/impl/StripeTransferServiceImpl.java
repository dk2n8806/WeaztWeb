package com.core.service.stripe.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.stripe.TransferAdapter;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeTransfer;
import com.common.util.date.DateInterval;
import com.core.dao.stripe.StripeTransferDao;
import com.core.service.stripe.StripeTransferService;

@Service
@Transactional
public class StripeTransferServiceImpl implements StripeTransferService {
	
	@Autowired private StripeTransferDao transferDao;
	
	@Override
	public StripeTransfer findById(Long id) {
		return transferDao.findById(id);
	}

	@Override
	public long getRowCount() {
		return transferDao.getRowCount();
	}

	@Override
	public StripeTransfer getReference(Long id) {
		return transferDao.getReference(id);
	}

	
	
	
	
/******************************************************************
 * {@link StripeTransferService#getByToken(String)}
 */
	@Override
	public StripeTransfer getByToken(String tokenId) {
		try {
			return transferDao.getByToken(tokenId);
		} catch(NoResultException e) {
			return null;
		}
	}


		
		
/**************************************************************************
 * 
 * {@link StripeTransferService#create(StripeAccount, TransferAdapter)}
 */
	@Override
	public StripeTransfer create(StripeAccount stripeAccount, TransferAdapter transferAdapter) {
		StripeTransfer transfer = StripeTransfer.create(stripeAccount, transferAdapter);
		if(transfer == null) {
			return null;
		}
		transferDao.persist(transfer);
		return transfer;
	}
	
	
/*****************************************************************************
 * 
 * {@link StripeTransferService#getByStripeAccount(StripeAccount, Long)}
 */
	@Override
	public StripeTransfer getByStripeAccount(StripeAccount stripeAccount,
			Long transferId) {
		if(stripeAccount == null || transferId == null) {
			return null;
		}
		try {
			return transferDao.getByStripeAccount(stripeAccount, transferId);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
/*****************************************************************************
 * Lookup a stripe_transfer by a merchant on a given transferId
 * 
 * {@link StripeTransferService#getByMerchant(Merchant, Long)}
 */
	@Override
	public StripeTransfer getByMerchant(Merchant merchant, Long transferId) {
		if(merchant == null || transferId == null) {
			return null;
		}
		try {
			return transferDao.getByMerchant(merchant, transferId);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
/*****************************************************************************
 * Retrieve a list of stripe_transfer
 * 
 * {@link StripeTransferService#getList(DateInterval, Pageable)}
 */
	@Override
	public List<StripeTransfer> getList(DateInterval dateInterval, Pageable pageable) {
		return transferDao.getList(dateInterval, pageable);
	}

	
	
/*****************************************************************************
 * Retrieve a list of stripe_transfer by a stripe_account
 * 
 * {@link StripeTransferService#getListByStripeAccount(StripeAccount, DateInterval, Pageable)}
 */
	@Override
	public List<StripeTransfer> getListByStripeAccount(StripeAccount stripeAccount
			, DateInterval dateInterval, Pageable pageable) {
		if(stripeAccount == null) {
			return new ArrayList<StripeTransfer>();
		}
		return transferDao.getListByStripeAccount(stripeAccount, dateInterval, pageable);
	}

	
	
/*****************************************************************************
 * Retrieve a list of stripe_transfer by a merchant
 * 
 * {@link StripeTransferService#getListByMerchant(Merchant, DateInterval, Pageable)}
 */
	@Override
	public List<StripeTransfer> getListByMerchant(Merchant merchant
			, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) {
			return new ArrayList<StripeTransfer>();
		}
		return transferDao.getListByMerchant(merchant, dateInterval, pageable);
	}

	
	
/*****************************************************************************
 * Count totalCharge stripe_transfer by a stripe_account
 * 
 * {@link StripeTransferService#countByStripeAccount(StripeAccount, DateInterval)}
 */
	@Override
	public long countByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval) {
		if(stripeAccount == null) {
			return 0;
		}
		try {
			return transferDao.countByStripeAccount(stripeAccount, dateInterval);
		}catch(NoResultException e) {
			return 0;
		}
	}

	
	
	
	
/*****************************************************************************
 * Count totalCharge stripe_transfer by a merchant
 * 
 * {@link StripeTransferService#countByMerchant(Merchant, DateInterval)}
 */
	@Override
	public long countByMerchant(Merchant merchant, DateInterval dateInterval) {
		if(merchant == null) {
			return 0;
		}
		try {
			return transferDao.countByMerchant(merchant, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	
}
