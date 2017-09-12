package com.core.dao.subscription.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.entity.subscription.Unsubscription;
import com.common.entity.subscription.Unsubscription_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.subscription.UnsubscriptionDao;

@Repository
public class UnsubscriptionDaoImpl 
	extends GenericJpaRepository<Unsubscription, Long> 
implements UnsubscriptionDao 
{

	@Override
	public List<Unsubscription> getUnsubscriptions(Account account, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Unsubscription> query = builder.createQuery(Unsubscription.class);
		Root<Unsubscription> root = query.from(Unsubscription.class);
		Join<Unsubscription, Subscription> joinSubscription = root.join(Unsubscription_.subscription);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Unsubscription_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	@Override
	public long countUnsubscriptions(Account account, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Unsubscription> root = query.from(Unsubscription.class);
		Join<Unsubscription, Subscription> joinSubscription = root.join(Unsubscription_.subscription);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Unsubscription_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
