package com.core.service.promo.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.promo.Promotion;
import com.common.util.date.DateInterval;
import com.core.dao.promo.PromotionDao;
import com.core.service.promo.PromotionService;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService{

	@Autowired private PromotionDao promotionDao;

	@Override
	public Promotion findById(Long id) {
		return promotionDao.findById(id);
	}

	@Override
	public Promotion getReference(Long id) {
		return promotionDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return promotionDao.getRowCount();
	}

	@Override
	public List<Promotion> getPromotions(Boolean isActive, DateInterval dateInterval, Pageable pageable) {
		return promotionDao.getPromotions(isActive, dateInterval, pageable);
	}

	@Override
	public long countPromotions(Boolean isActive, DateInterval dateInterval) {
		try {
			return promotionDao.countPromotions(isActive, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	@Override
	public Promotion save(String code, int value, Date startOn, Date endOn) {
		Promotion promotion = Promotion.create(code, value, startOn, endOn);
		if(promotion != null) 
			promotionDao.persist(promotion);
		return promotion;
	}

	
	
	@Override
	public Promotion getByCode(String code) {
		try {
			return promotionDao.getByCode(code.toUpperCase());
		} catch(NullPointerException | NoResultException e) {
			return null;
		}
	}
	
}
