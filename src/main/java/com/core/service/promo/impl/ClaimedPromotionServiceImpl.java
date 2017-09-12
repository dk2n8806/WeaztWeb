package com.core.service.promo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.Promotion;
import com.core.dao.promo.ClaimedPromotionDao;
import com.core.service.promo.ClaimedPromotionService;

@Service
@Transactional
public class ClaimedPromotionServiceImpl implements ClaimedPromotionService{

	@Autowired private ClaimedPromotionDao claimedPromotionDao;
	
	@Override
	public ClaimedPromotion findById(Long id) {
		return claimedPromotionDao.findById(id);
	}

	@Override
	public ClaimedPromotion getReference(Long id) {
		return claimedPromotionDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return claimedPromotionDao.getRowCount();
	}

	@Override
	public ClaimedPromotion getByAccount(Account account, Promotion promotion) {
		if(account == null) return null;
		try {
			return claimedPromotionDao.getByAccount(account, promotion);
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<ClaimedPromotion> getByAccount(Account account, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return claimedPromotionDao.getByAccount(account, pageable);
	}
	
	

	@Override
	public long countByAccount(Account account) {
		try {
			return claimedPromotionDao.countByAccount(account);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	

	@Override
	public ClaimedPromotion save(Account account, Promotion promotion) {
		ClaimedPromotion claim = getByAccount(account, promotion);
		// Prevent client to apply a promotion more than one.
		if(claim != null)  return null;			
		
		// Allow client to claim the promotion if hasnot
		claim = ClaimedPromotion.create(account, promotion);
		if(claim != null) {
			claimedPromotionDao.persist(claim);
		}
		return claim;
	}

}
