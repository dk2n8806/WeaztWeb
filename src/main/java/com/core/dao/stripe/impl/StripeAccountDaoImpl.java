package com.core.dao.stripe.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.adapter.stripe.AccountAdapter_;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeAccount_;
import com.common.entity.stripe.StripeStatus;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.stripe.StripeAccountDao;

/************************************************************************
 * 
 * @author dk
 *
 */
@Repository
public class StripeAccountDaoImpl 
extends GenericJpaRepository<StripeAccount, Long>
implements StripeAccountDao {

	
	
/***************************************************
 * @query
 * [
 * 		Select STRIPE_ACC 
 * 		From {@link StripeAccount} STRIPE_ACC
 * 		Where STRIPE_ACC.accountAdapter.tokenId = tokenId
 * ]
 * 
 * {@link StripeAccountDao#getByToken(String)}
 */
	@Override
	public StripeAccount getByToken(String tokenId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeAccount> query = builder.createQuery(StripeAccount.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		// STRIPE_ACC.accountAdapter.tokenId = tokenId
		query.select(root).where(builder.equal(root.get(StripeAccount_.accountAdapter)
											.get(AccountAdapter_.tokenId), tokenId));
		return entityManager.createQuery(query).getSingleResult();
	}


	
	
	
	
/***************************************************
 * 
 * @query
 * [
 * 		Select STRIPE_ACC 
 * 		From {@link StripeAccount} STRIPE_ACC
 * 		Where STRIPE_ACC.status = :status
 * 			Order by STRIPE_ACC.id DESC
 * ]
 * 
 */
	@Override
	public List<StripeAccount> getList(StripeStatus status, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeAccount> query = builder.createQuery(StripeAccount.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			
			// STRIPE_ACC.status = :status
			predicates.add(builder.equal(root.get(StripeAccount_.status), status));
		}
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]))
			.orderBy(builder.desc(root.get(StripeAccount_.id)));
		return this.getResultList(query, pageable);
	}


	
	
/***************************************************
 * @query
 * [
 * 		Select count(STRIPE_ACC) 
 * 		From {@link StripeAccount} STRIPE_ACC
 * 		Where STRIPE_ACC.status = :status
 * ]
 * 
 * {@link StripeAccountDao#count(StripeStatus)}
 */
	@Override
	public long count(StripeStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			
			// STRIPE_ACC.status = :status
			predicates.add(builder.equal(root.get(StripeAccount_.status), status));
		}
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}




	
/***************************************************
 * @query
 * [
 * 		Select STRIPE_ACC 
 * 		From {@link StripeAccount} STRIPE_ACC
 * 		Where STRIPE_ACC.merchant = :merchant
 * 			And STRIPE_ACC.status = :status
 * 		Order By STRIPE_ACC.id DESC
 * ]
 * 
 * {@link StripeAccountDao#getListByMerchant(Merchant, StripeStatus, Pageable)}
 */	
	@Override
	public List<StripeAccount> getListByMerchant(Merchant merchant,
			StripeStatus status, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeAccount> query = builder.createQuery(StripeAccount.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// STRIPE_ACC.merchant = :merchant
		predicates.add(builder.equal(root.get(StripeAccount_.merchant), merchant));
		if(status != null) {
			
			// STRIPE_ACC.status = :status
			predicates.add(builder.equal(root.get(StripeAccount_.status), status));
		}
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]))
			.orderBy(builder.desc(root.get(StripeAccount_.id)));
		return this.getResultList(query, pageable);
	}
	
	
	
	
	
/********************************************************************************
 * @query
 * [
 * 		Select count(STRIPE_ACC) 
 * 		From {@link StripeAccount} STRIPE_ACC
 * 		Where STRIPE_ACC.merchant = :merchant
 * 			And STRIPE_ACC.status = :status
 * ]
 * 
 * 
 */
	@Override
	public long countByMerchant(Merchant merchant, StripeStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		// STRIPE_ACC.merchant = :merchant
		predicates.add(builder.equal(root.get(StripeAccount_.merchant), merchant));
		if(status != null) {
			
			// STRIPE_ACC.status = :status
			predicates.add(builder.equal(root.get(StripeAccount_.status), status));
		}
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}





/********************************************************************************
 * 
 * @query
 * [
 * 		Select STRIPE 
 * 		From {@link StripeAccount} STRIPE
 * 		Where STRIPE.merchant = :merchant
 * 			And STRIPE.id = :stripeId
 * ]
 * 
 * {@link StripeAccountDao#getByMerchant(Merchant, Long)}
 */
	@Override
	public StripeAccount getByMerchant(Merchant merchant, Long stripeId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeAccount> query = builder.createQuery(StripeAccount.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		query.select(root)
			.where(builder.equal(root.get(StripeAccount_.merchant), merchant)
					, builder.equal(root.get(StripeAccount_.id), stripeId));
		return entityManager.createQuery(query).getSingleResult();
	}



	
	
/***************************************************
 * Update stripe_account status
 * 
 * @query
 * [
 * 		Update {@link StripeAccount} STRIPE
 * 		Set STRIPE.status = :status
 * 		Where STRIPE = :stripe(s)
 * ]	
 * 
 * {@link StripeAccountDao#updateStatus(List, StripeStatus)}
 */
	@Override
	public void updateStatus(List<StripeAccount> stripes, StripeStatus status) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<StripeAccount> query = builder.createCriteriaUpdate(StripeAccount.class);
		Root<StripeAccount> root = query.from(StripeAccount.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		for(StripeAccount stripe : stripes) {
			predicates.add(builder.equal(root, stripe));
		}
		query.set(root.get(StripeAccount_.status), status)
			.where(predicates.toArray(new Predicate[]{}));
		
		entityManager.createQuery(query).executeUpdate();
	}

}
