package com.core.dao.payment.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.payment.SubscriptionPayment_;
import com.common.entity.subscription.Subscription;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.payment.SubscriptionPaymentDao;

@Repository
public class SubscriptionPaymentDaoImpl 
	extends GenericJpaRepository<SubscriptionPayment, Long>
implements SubscriptionPaymentDao
{

	

	
	/** :::
	 * <p>Lookup subscription_payment entities</p>
	 * 
	 * @query
	 * [
	 * 		Select PFrom {@link SubscriptionPayment} P
	 * 		Where P.account = :account
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link SubscriptionPaymentDao#countPayments(Account, PaymentStatus, DateInterval)}
	 * ::: */
	@Override
	public List<SubscriptionPayment> getPayments(
						Account account, PaymentStatus status, DateInterval dateInterval, Pageable pageable)
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubscriptionPayment> query = builder.createQuery(SubscriptionPayment.class);
		Root<SubscriptionPayment> root = query.from(SubscriptionPayment.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(root.get(SubscriptionPayment_.account), account));
		if(status != null) 
			predicates.add(builder.equal(root.get(SubscriptionPayment_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(SubscriptionPayment_.createdOn)
																		, dateInterval.getFrom(), dateInterval.getTo()))	;
		
		query.select(root).where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	
	
	
	
	
	/** :::
	 * <p>Count total subscription_payment entities</p>
	 * 
	 * @query
	 * [
	 * 		Select count(P) From {@link SubscriptionPayment} P
	 * 		Where P.account = :account
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link SubscriptionPaymentDao#countPayments(Account, PaymentStatus, DateInterval)}
	 * ::: */
	@Override
	public long countPayments(Account account, PaymentStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SubscriptionPayment> root = query.from(SubscriptionPayment.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(root.get(SubscriptionPayment_.account), account));
		if(status != null) 
			predicates.add(builder.equal(root.get(SubscriptionPayment_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(SubscriptionPayment_.createdOn)
																		, dateInterval.getFrom(), dateInterval.getTo()))	;
		
		query.select(builder.count(root)).where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}






	/** 
	 * 
	 * {@link SubscriptionPaymentDao#getBySubscription(Subscription)}
	 */
	@Override
	public SubscriptionPayment getBySubscription(Subscription subscription) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubscriptionPayment> query = 
							builder.createQuery(SubscriptionPayment.class);
		Root<SubscriptionPayment> root = query.from(SubscriptionPayment.class);
		query.select(root)
				.where(builder.equal(root.get(SubscriptionPayment_.subscription), subscription));
		return entityManager.createQuery(query).getSingleResult();
	}






	/** 
	 * <p>Update payment request status by subscriptions</p>
	 * 
	 * @query
	 * [
	 * 		Update SP From {@link SubscriptionPayment} SP
	 * 		Set SP.requested = :isRequested
	 * 		Where (or) SP.subscription = :subscription
	 * 			And SP.status = :status
	 * 			And SP.requested = :curRequest
	 * 		Order By  SP Desc
	 * ]
	 * 
	 * {@link SubscriptionPaymentDao#updatePaymentRequestBySubscriptions(List, PaymentStatus, Boolean, Boolean)}
	 */
	@Override
	public void updatePaymentRequestBySubscriptions(
			List<Subscription> subscriptions, PaymentStatus status,Boolean curRequest, Boolean isRequested) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<SubscriptionPayment> query
								= builder.createCriteriaUpdate(SubscriptionPayment.class);
		Root<SubscriptionPayment> root = query.from(SubscriptionPayment.class);
		
		
		List<Predicate> sPredicates = new ArrayList<>();
		for(Subscription subscription : subscriptions) 
			sPredicates.add(builder.equal(root.get(SubscriptionPayment_.subscription), subscription));
		
		query.set(root.get(SubscriptionPayment_.requested), isRequested)
				.where(builder.or(sPredicates.toArray(new Predicate[]{}))
						, builder.equal(root.get(SubscriptionPayment_.status), status)
						, builder.equal(root.get(SubscriptionPayment_.requested), curRequest)
					);
		entityManager.createQuery(query).executeUpdate();
	}







}
