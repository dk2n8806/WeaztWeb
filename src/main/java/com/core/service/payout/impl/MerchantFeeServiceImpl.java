package com.core.service.payout.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.MerchantFee;
import com.common.type.FeeStatus;
import com.common.util.date.DateInterval;
import com.core.dao.payout.MerchantFeeDao;
import com.core.service.payout.MerchantFeeService;

@Service
@Transactional
public class MerchantFeeServiceImpl implements MerchantFeeService
{
	
	@Autowired private MerchantFeeDao merchantFeeDao;

	@Override
	public MerchantFee save(MerchantFee fee) {
		if(fee != null) {
			merchantFeeDao.persist(fee);
		}
		return fee;
	}

	@Override
	public List<MerchantFee> getByMerchant(Merchant merchant
			, FeeStatus status, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) return new ArrayList<>();
		return merchantFeeDao.getFee(merchant, status, dateInterval, pageable);
	}

	@Override
	public long countByMerchant(Merchant merchant, FeeStatus status, DateInterval dateInterval) {
		if(merchant == null) return 0;
		try {
			return merchantFeeDao.countFees(merchant, status, dateInterval);
		} catch(NoResultException e){
			return 0;
		}
	}

	@Override
	public int getTotalFeeByMerchant(Merchant merchant, FeeStatus status, DateInterval dateInterval) {
		if(merchant == null) return 0;
		try {
			return (int) merchantFeeDao.getTotalFee(merchant, status, dateInterval);
		} catch(RuntimeException e){
			return 0;
		}}

}
