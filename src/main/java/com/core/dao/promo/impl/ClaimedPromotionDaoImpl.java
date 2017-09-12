package com.core.dao.promo.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.ClaimedPromotion_;
import com.common.entity.promo.Promotion;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.promo.ClaimedPromotionDao;


/**
 * 
 * @author dk2n_
 *
 */
@Repository
public class ClaimedPromotionDaoImpl 
extends GenericJpaRepository<ClaimedPromotion, Long>
implements ClaimedPromotionDao {

	@Override
	public List<ClaimedPromotion> getByAccount(Account account, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClaimedPromotion> query = builder.createQuery(ClaimedPromotion.class);
		Root<ClaimedPromotion> root = query.from(ClaimedPromotion.class);
		
		query.select(root)
			.where(builder.equal(root.get(ClaimedPromotion_.account), account))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	@Override
	public long countByAccount(Account account) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ClaimedPromotion> root = query.from(ClaimedPromotion.class);
		
		query.select(builder.count(root))
			.where(builder.equal(root.get(ClaimedPromotion_.account), account));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	@Override
	public ClaimedPromotion getByAccount(Account account, Promotion promotion) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClaimedPromotion> query = builder.createQuery(ClaimedPromotion.class);
		Root<ClaimedPromotion> root = query.from(ClaimedPromotion.class);
		
		query.select(root)
			.where(builder.equal(root.get(ClaimedPromotion_.account), account)
					, builder.equal(root.get(ClaimedPromotion_.promotion), promotion));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
