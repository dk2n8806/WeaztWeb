package com.core.dao.stripe.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.adapter.stripe.ChargeAdapter_;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCharge_;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeCustomer_;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.stripe.StripeChargeDao;


/**************************************************************************
 * 
 * @author dk
 *
 */
@Repository
public class StripeChargeDaoImpl extends GenericJpaRepository<StripeCharge, Long>
implements StripeChargeDao{

	
/***********************************************************************
 * @query
 * [
 * 		Select CHARGE 
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.adapter.tokenId = :customerAdapter
 * ]
 * 
 * {@link StripeChargeDao#getByToken(String)}
 */
	@Override
	public StripeCharge getByToken(String tokenId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		query.select(root).where(builder.equal(root.get(StripeCharge_.chargeAdapter)
													.get(ChargeAdapter_.tokenId), tokenId));
		return entityManager.createQuery(query).getSingleResult();
	}



	
	


	
	
/***********************************************************************
 * @query
 * [
 * 		Select CHARGE
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.status = :status
 * ]
 * 
 * {@link StripeChargeDao#getCharges(StripeStatus, Pageable)}
 */
	@Override
	public List<StripeCharge> getCharges(Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		query.select(root)
				.orderBy(builder.desc(root.get(StripeCharge_.createdOn)));
		return entityManager.createQuery(query).getResultList();
	}




	

	
	
/***********************************************************************
 * @query
 * [
 * 		Select CHARGE
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer = :stripeCustomer
 * ]
 * 
 * {@link StripeChargeDao#getListByStripeCustomer(StripeCustomer, Pageable)}
 */
	@Override
	public List<StripeCharge> getListByStripeCustomer(
			StripeCustomer stripeCustomer, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer), stripeCustomer));
		
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]))
				.orderBy(builder.desc(root.get(StripeCharge_.id)));
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	


	

	
	
/***********************************************************************
 * @query
 * [
 * 		Select CHARGE
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer.account = :account
 * ]
 * 
 * {@link StripeChargeDao#getListByAccount(Account, Pageable)}
 */
	@Override
	public List<StripeCharge> getListByAccount(Account account,
			Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer.account = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer)
										 .get(StripeCustomer_.account), account));
		
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]))
				.orderBy(builder.desc(root.get(StripeCharge_.createdOn)));
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	


	

	
	
/***********************************************************************
 * @query
 * [
 * 		Select count(CHARGE)
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer = :stripeCustomer
 * ]
 * 
 * {@link StripeChargeDao#countByStripeCustomer(StripeCustomer)}
 */
	@Override
	public long countByStripeCustomer(StripeCustomer stripeCustomer) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer), stripeCustomer));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]))
			;
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	
	
	
	


	

	
	
/***********************************************************************
 * @query
 * [
 * 		Select count(CHARGE)
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer.account = :stripeCustomer
 * ]
 * 
 * {@link StripeChargeDao#countByAccount(Account)}
 */
	@Override
	public long countByAccount(Account account) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer)
				 			  			 .get(StripeCustomer_.account), account));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]))
			;
		return entityManager.createQuery(query).getSingleResult();
	}





	
	
	
/******************************************************************************
 * @query
 * [
 * 		Select CHARGE
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer = :account
 * 			And CHARGE.id = :chargeId
 * ]
 * 
 * {@link StripeChargeDao#getByStripeCustomer(StripeCustomer, Long)}
 */
	@Override
	public StripeCharge getByStripeCustomer(StripeCustomer customer, Long chargeId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer.account = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer), customer));
		
		// CHARGE.id = :chargeId
		predicates.add(builder.equal(root.get(StripeCharge_.id), chargeId));
		
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	
	
	
	
	
	

	
	
/******************************************************************************
 * @query
 * [
 * 		Select CHARGE
 * 		From {@link StripeCharge} CHARGE
 * 		Where CHARGE.stripeCustomer.account = :account
 * 			And CHARGE.id = :chargeId
 * ]
 * 
 * {@link StripeChargeDao#getByAccount(Account, Long)}
 */
	@Override
	public StripeCharge getByAccount(Account account, Long chargeId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCharge> query = builder.createQuery(StripeCharge.class);
		Root<StripeCharge> root = query.from(StripeCharge.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// CHARGE.stripeCustomer.account = :stripeCustomer
		predicates.add(builder.equal(root.get(StripeCharge_.stripeCustomer)
										 .get(StripeCustomer_.account), account));
		// CHARGE.id = :chargeId
		predicates.add(builder.equal(root.get(StripeCharge_.id), chargeId));
		
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
}
