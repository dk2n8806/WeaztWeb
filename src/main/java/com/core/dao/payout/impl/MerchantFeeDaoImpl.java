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
import com.common.entity.payout.MerchantFee;
import com.common.entity.payout.MerchantFee_;
import com.common.type.FeeStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.payout.MerchantFeeDao;

@Repository
public class MerchantFeeDaoImpl 
	extends GenericJpaRepository<MerchantFee, Long>
implements MerchantFeeDao
{

	/** :::
	 * 
	 * @query
	 * [
	 * 		Select count(*) From {@link MerchantFee}
	 * 		Where merchant =:merchant
	 * 			And status = :status
	 * 			And createdOn Between :from And :to
	 * 		Order By id DESC
	 * ]		
	 * 
	 * {@link MerchantFeeDao#getFee(Merchant, FeeStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<MerchantFee> getFee(Merchant merchant
			, FeeStatus status, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MerchantFee> query = builder.createQuery(MerchantFee.class);
		Root<MerchantFee> root = query.from(MerchantFee.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(MerchantFee_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(MerchantFee_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(MerchantFee_.createdOn)
													, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	
	/** :::
	 * 
	 * @query
	 * [
	 * 		Select count(*) From {@link MerchantFee}
	 * 		Where merchant =:merchant
	 * 			And status = :status
	 * 			And createdOn Between :from And :to
	 * ]		
	 * 
	 * {@link MerchantFeeDao#countFees(Merchant, FeeStatus, DateInterval)}
	 * ::: */
	@Override
	public long countFees(Merchant merchant, FeeStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<MerchantFee> root = query.from(MerchantFee.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(MerchantFee_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(MerchantFee_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(MerchantFee_.createdOn)
													, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	/** :::
	 * 
	 * @query
	 * [
	 * 		Select sum(*) From {@link MerchantFee}
	 * 		Where merchant =:merchant
	 * 			And status = :status
	 * 			And createdOn Between :from And :to
	 * ]		
	 * 
	 * 
	 * {@link MerchantFeeDao#getTotalFee(Merchant, FeeStatus, DateInterval)}
	 * ::: */
	@Override
	public long getTotalFee(Merchant merchant, FeeStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<MerchantFee> root = query.from(MerchantFee.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(MerchantFee_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(MerchantFee_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(MerchantFee_.createdOn)
													, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(builder.sumAsLong(root.get(MerchantFee_.amount)))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}


}
