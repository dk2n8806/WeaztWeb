package com.core.service.subscription.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Unsubscription;
import com.common.util.date.DateInterval;
import com.core.dao.subscription.UnsubscriptionDao;
import com.core.service.subscription.UnsubscriptionService;

@Service
@Transactional
public class UnsubscriptionServiceImpl implements UnsubscriptionService{

	@Autowired private UnsubscriptionDao unsubscriptionDao;

	@Override
	public Unsubscription findById(Long id) {
		return unsubscriptionDao.findById(id);
	}

	@Override
	public Unsubscription getReference(Long id) {
		return unsubscriptionDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return unsubscriptionDao.getRowCount();
	}

	@Override
	public List<Unsubscription> getUnsubscriptions(DateInterval dateInterval, Pageable pageable) {
		return unsubscriptionDao.getUnsubscriptions(null, dateInterval, pageable);
	}

	@Override
	public List<Unsubscription> getByAccount(Account account, DateInterval dateInterval, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return unsubscriptionDao.getUnsubscriptions(account, dateInterval, pageable);
	}

	@Override
	public long countUnsubscriptions(DateInterval dateInterval) {
		try {
			return unsubscriptionDao.countUnsubscriptions(null, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	@Override
	public long countByAccount(Account account, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return unsubscriptionDao.countUnsubscriptions(account, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	@Override
	public Unsubscription save(Subscription subscription) {
		Unsubscription unsubscription  = Unsubscription.create(subscription);
		if(unsubscription != null) {
			unsubscriptionDao.persist(unsubscription);
		}
		return unsubscription;
	}
}
