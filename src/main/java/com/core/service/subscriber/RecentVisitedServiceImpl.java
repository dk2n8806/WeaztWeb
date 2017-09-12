package com.core.service.subscriber;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.RecentVisited;
import com.common.type.ProductStatus;
import com.core.dao.subscriber.RecentVisitedDao;

@Service
@Transactional
public class RecentVisitedServiceImpl implements RecentVisitedService{

	@Autowired private RecentVisitedDao recentVisitedDao;
	
	@Override
	public RecentVisited save(Account account, Product product) {
		RecentVisited recent = this.getRecentVisited(account, product);
		if(recent == null) {
			recent = RecentVisited.create(account, product);
			if(recent != null) {
				recentVisitedDao.persist(recent);
			}
		} else {
			if(recent.isActive()) {
				recent.setLastVisited(new Date());
				recentVisitedDao.update(recent);
			} else {
				recent = null;
			}
		}
		
		return recent;
	}

	@Override
	public RecentVisited getRecentVisited(Account account, Product product) {
		try {
			if(account == null || product == null)
				return null;
			return recentVisitedDao.getRecentVisited(account, product);
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void deactive(Account account, Product product) {
		RecentVisited recent = this.getRecentVisited(account, product);
		if(recent != null) {
			recent.setActive(false);
			recentVisitedDao.update(recent);
		}
	}

	@Override
	public long countByAccount(Account account, ProductStatus status) {
		if(account == null) return 0;
		try {
			return recentVisitedDao.count(account, status);
		} catch(NoResultException e) {
			return 0;
		}
	}

}
