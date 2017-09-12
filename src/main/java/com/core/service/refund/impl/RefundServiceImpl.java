package com.core.service.refund.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.refund.Refund;
import com.common.entity.refund.RefundStatus;
import com.common.util.date.DateInterval;
import com.core.dao.refund.RefundDao;
import com.core.service.refund.RefundService;

@Service
@Transactional
public class RefundServiceImpl implements RefundService{

	@Autowired private RefundDao refundDao;
	
	@Override
	public Refund findById(Long id) {
		return refundDao.findById(id);
	}

	@Override
	public Refund getReference(Long id) {
		return refundDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return refundDao.getRowCount();
	}

	
	
	/** :::
	 * <p>Create & Persist a new refund entity</p>
	 * 
	 * {@link RefundService#save(Refund)}
	 * ::: */
	@Override
	public Refund save(Refund refund) {
		if(refund != null)
			refundDao.persist(refund);
		return refund;
	}

	@Override
	public List<Refund> getList(RefundStatus status, DateInterval dateInterval, Pageable pageable) {
		return refundDao.getList(null, status, dateInterval, pageable);
	}

	@Override
	public List<Refund> getByAccount(Account account, RefundStatus status, DateInterval dateInterval, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return refundDao.getList(account, status, dateInterval, pageable);
	}

	@Override
	public long count(RefundStatus status, DateInterval dateInterval) {
		try {
			return refundDao.count(null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	@Override
	public long countByAccount(Account account, RefundStatus status, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return refundDao.count(account, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

}
