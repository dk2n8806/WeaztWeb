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

import com.common.entity.payment.Payment;
import com.common.entity.payment.Payment_;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.payment.PaymentDao;

@Repository
public class PaymentDaoImpl extends GenericJpaRepository<Payment, Long> implements PaymentDao 
{

	@Override
	public void updatePaymentStatus(Payment payment, PaymentStatus oldStatus, PaymentStatus newStatus) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Payment> query = builder.createCriteriaUpdate(Payment.class);
		Root<Payment> root = query.from(Payment.class);
		
		if(payment != null && oldStatus != null && newStatus != null) {
			query.set(root.get(Payment_.status), newStatus)
				.where(builder.equal(root, payment)
						, builder.equal(root.get(Payment_.status), oldStatus));
			entityManager.createQuery(query).executeUpdate();
		}
	}
	
	
	
	
		
	/** :::
	 * {@link PaymentDao#getPayments}
	 */
	@Override
	public List<Payment> getPayments(PaymentStatus status
				, Boolean isRequested, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
		Root<Payment> root = query.from(Payment.class);
		
		
		List<Predicate> predicates = new ArrayList<>();
		if(status != null)
			predicates.add(builder.equal(root.get(Payment_.status), status));
		if(isRequested != null)
			predicates.add(builder.equal(root.get(Payment_.requested), isRequested));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Payment_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo()));	
		
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}
	
	
	

	/** :::
	 * {@link PaymentDao#countsPayments}
	 */
	@Override
	public long countPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Payment> root = query.from(Payment.class);
		
		
		List<Predicate> predicates = new ArrayList<>();
		if(status != null)
			predicates.add(builder.equal(root.get(Payment_.status), status));
		if(isRequested != null)
			predicates.add(builder.equal(root.get(Payment_.requested), isRequested));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Payment_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo()));	
		
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}


}
