package com.core.service.payout.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.Payout;
import com.common.type.PayoutStatus;
import com.common.util.date.DateInterval;
import com.core.dao.payout.PayoutDao;
import com.core.service.payout.PayoutService;

@Service 
@Transactional
public class PayoutServiceImpl implements PayoutService{

	@Autowired private PayoutDao payoutDao;
	
	@Override
	public Payout findById(Long id) {
		return payoutDao.findById(id);
	}

	@Override
	public Payout getReference(Long id) {
		return payoutDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return payoutDao.getRowCount();
	}

	@Override
	public Payout save(Payout payout) {
		if(payout != null)
			payoutDao.persist(payout);
		return payout;
	}

	@Override
	public List<Payout> getPayouts(
			PayoutStatus status, DateInterval dateInterval, Pageable pageable)
	{
		return payoutDao.getPayouts(null, status, dateInterval, pageable);
	}

	@Override
	public long countPayouts(PayoutStatus status, DateInterval dateInterval) {
		try {
			return payoutDao.countPayouts(null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	@Override
	public int getTotalAmount(PayoutStatus status, DateInterval dateInterval) {
		try {
			return (int) payoutDao.getTotalAmount(null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** ::: 
	 * <p>Count total payout entities by a merchant</p>
	 * 
	 * {@link PayoutService#countByMerchant(Merchant, PayoutStatus, DateInterval)}
	 * ::: */
	@Override
	public long countByMerchant(Merchant merchant, PayoutStatus status, DateInterval dateInterval) {
		if(merchant  == null) return 0;
		try {
			return payoutDao.countPayouts(merchant, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** :::
	 * <p>Retrieve a list of payout entities</p>
	 * 
	 * {@link PayoutService#getByMerchant(Merchant, PayoutStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Payout> getByMerchant(Merchant merchant
			, PayoutStatus status, DateInterval dateInterval,Pageable pageable) {
		if(merchant == null) return new ArrayList<>();
		return payoutDao.getPayouts(merchant, status, dateInterval, pageable);
	}

	
	
	/** :::
	 * <p>Sum payout amount by a merchant</p>
	 * 
	 * {@link PayoutService#getTotalAmountByMerchant(Merchant, PayoutStatus, DateInterval)}
	 * ::: */
	@Override
	public int getTotalAmountByMerchant(Merchant merchant, PayoutStatus status, DateInterval dateInterval) {
		if(merchant == null) return 0;
		try {
			return (int) payoutDao.getTotalAmount(merchant, status, dateInterval);
		} catch(NoResultException | NullPointerException e) {
			return 0;
		}
	}

}
