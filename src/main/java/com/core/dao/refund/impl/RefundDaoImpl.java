package com.core.dao.refund.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.refund.Refund;
import com.common.entity.refund.RefundStatus;
import com.common.entity.refund.Refund_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.refund.RefundDao;

@Repository
public class RefundDaoImpl 
	extends GenericJpaRepository<Refund, Long> 
implements RefundDao
{

	@Override
	public List<Refund> getList(Account account, RefundStatus status, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Refund> query = builder.createQuery(Refund.class);
		Root<Refund> root = query.from(Refund.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null)
			predicates.add(builder.equal(root.get(Refund_.account), account));
		if(status != null)
			predicates.add(builder.equal(root.get(Refund_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(Refund_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	
	
	
	@Override
	public long count(Account account, RefundStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Refund> root = query.from(Refund.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null)
			predicates.add(builder.equal(root.get(Refund_.account), account));
		if(status != null)
			predicates.add(builder.equal(root.get(Refund_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(Refund_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
