package com.core.dao.promo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.promo.Promotion;
import com.common.entity.promo.Promotion_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.promo.PromotionDao;

@Repository
public class PromotionDaoImpl 
	extends GenericJpaRepository<Promotion, Long>
implements PromotionDao 
{

	@Override
	public List<Promotion> getPromotions(Boolean isActive, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
		Root<Promotion> root = query.from(Promotion.class);
		List<Predicate> predicates = new ArrayList<>();
		if(isActive == null) 
			predicates.add(builder.equal(root.get(Promotion_.active), isActive));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Promotion_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	
	
	
	@Override
	public long countPromotions(Boolean isActive, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Promotion> root = query.from(Promotion.class);
		List<Predicate> predicates = new ArrayList<>();
		if(isActive == null) 
			predicates.add(builder.equal(root.get(Promotion_.active), isActive));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Promotion_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	@Override
	public Promotion getByCode(String code) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Promotion> query = builder.createQuery(Promotion.class);
		Root<Promotion> root = query.from(Promotion.class);
		
		query.select(root)
				.where(builder.equal(root.get(Promotion_.code), code));
		return entityManager.createQuery(query).getSingleResult();
	}

}
