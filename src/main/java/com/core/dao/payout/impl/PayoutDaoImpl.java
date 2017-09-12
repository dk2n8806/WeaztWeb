package com.core.dao.payout.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.Payout;
import com.common.entity.payout.Payout_;
import com.common.type.PayoutStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.payout.PayoutDao;

@Repository
public class PayoutDaoImpl 
	extends GenericJpaRepository<Payout, Long>
implements PayoutDao
{




	/** :::
	 * <p>Retrieve a list of payout entities</p>
	 * 
	 * @query
	 * [
	 * 		Select P from {@link Payout} P
	 * 		Where P.merchant = :merchant
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from And :to
	 * 		Order By P DESC
	 * ]
	 * 
	 * {@link PayoutDao#getPayouts(Merchant, PayoutStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Payout> getPayouts(Merchant merchant
			, PayoutStatus status, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Payout> query = builder.createQuery(Payout.class);
		Root<Payout> root = query.from(Payout.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(Payout_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(Payout_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Payout_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(root).where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	



	/** :::
	 * <p>Count total payout entities</p>
	 * 
	 * @query
	 * [
	 * 		Select count(P) from {@link Payout} P
	 * 		Where P.merchant = :merchant
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link PayoutDao#countPayouts(Merchant, PayoutStatus, DateInterval)}
	 * ::: */
	@Override
	public long countPayouts(Merchant merchant, PayoutStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Payout> root = query.from(Payout.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(Payout_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(Payout_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Payout_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root)).where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}



	/** :::
	 * <p>Sum the total payout amount</p>
	 * 
	 * @query
	 * [
	 * 		Select sum(P.amount) from {@link Payout} P
	 * 		Where P.merchant = :merchant
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link PayoutDao#getTotalAmount(Merchant, PayoutStatus, DateInterval)}
	 * ::: */
	@Override
	public long getTotalAmount(Merchant merchant, PayoutStatus status, DateInterval dateInterval) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Payout> root = query.from(Payout.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(Payout_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(Payout_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Payout_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.sumAsLong(root.get(Payout_.amount)))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}



}
