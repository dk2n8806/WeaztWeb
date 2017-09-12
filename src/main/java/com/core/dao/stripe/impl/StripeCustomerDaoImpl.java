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

import com.common.adapter.stripe.CustomerAdapter_;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeCustomer_;
import com.common.entity.stripe.StripeStatus;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.stripe.StripeCustomerDao;


/*********************************************************************
 * 
 * @author dk
 *
 */
@Repository
public class StripeCustomerDaoImpl 
extends GenericJpaRepository<StripeCustomer, Long>
implements StripeCustomerDao {


	
	
	
/*************************************************************************
 * @query
 * [
 * 		Select S 
 * 		From {@link StripeCustomer} S
 * 		Where S.adapter.tokenId = :customerAdapter
 * ]
 * 
 * {@link StripeCustomerDao#findByToken(String)}
 */
	@Override
	public StripeCustomer findByToken(String tokenId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCustomer> query = builder.createQuery(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		query.select(root)
			.where(builder.equal(root.get(StripeCustomer_.customerAdapter)
									.get(CustomerAdapter_.tokenId), tokenId));
		return entityManager.createQuery(query).getSingleResult();
	}

	


	
	
	
	
/*************************************************************************
 * @query
 * [
 * 		Select S 
 * 		From {@link StripeCustomer} S
 * 		Where S.status = :status
 * 		Order By S.id DESC
 * 		Offset pageable.getFirstResult
 * 		Limit pageable.getMaxResult
 * ]
 * 
 * {@link StripeCustomerDao#getList(StripeStatus, Pageable)}
 */
	@Override
	public List<StripeCustomer> getList(StripeStatus status,
			Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCustomer> query = builder.createQuery(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(status != null) {
			predicates.add(builder.equal(root.get(StripeCustomer_.status), status));
		}
		query.select(root)
				.where(predicates.toArray(new Predicate[predicates.size()]))
				.orderBy(builder.desc(root.get(StripeCustomer_.id)));
		return this.getResultList(query, pageable);
	}


	
	
	
/*************************************************************************
 * @query
 * [
 * 		Select count(S) 
 * 		From {@link StripeCustomer} S
 * 		Where S.status = :status
 * ]
 * 
 * {@link StripeCustomerDao#count(StripeStatus)}
 */
	@Override
	public long count(StripeStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(status != null) {
			predicates.add(builder.equal(root.get(StripeCustomer_.status), status));
		}
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}




	
	

	
/*************************************************************************
 * @query
 * [
 * 		Select STRIPE 
 * 		From {@link StripeCustomer} STRIPE
 * 		Where STRIPE.account = :account
 * 			And STRIPE.status = :status
 * 		Order By STRIPE.id
 * ]
 * 
 * {@link StripeCustomerDao#getListByAccount(Account, StripeStatus, Pageable)}
 */
	@Override
	public List<StripeCustomer> getListByAccount(Account account,
			StripeStatus status, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCustomer> query = builder.createQuery(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(StripeCustomer_.account), account));
		if(status != null) {
			predicates.add(builder.equal(root.get(StripeCustomer_.status), status));
		}
		query.select(root)
				.where(predicates.toArray(new Predicate[predicates.size()]))
				.orderBy(builder.desc(root.get(StripeCustomer_.id)));
		return this.getResultList(query, pageable);
	}
	
	
	
	
	
	

/*************************************************************************
 * @query
 * [
 * 		Select count(S) 
 * 		From {@link StripeCustomer} S
 * 		Where S.account = :account
 * 			And S.status = :status
 * ]
 * {@link StripeCustomerDao#countByAccount(Account, StripeStatus)}
 */
	@Override
	public long countByAccount(Account account, StripeStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(builder.equal(root.get(StripeCustomer_.account), account));
		if(status != null) {
			predicates.add(builder.equal(root.get(StripeCustomer_.status), status));
		}
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}





	
	
/**********************************************************************************
 * Lookup a stripe_customer on a given stripeId by an account
 * 
 * @query
 * [
 * 		Select STRIPE 
 * 		From {@link StripeCustomer} STRIPE
 * 		Where STRIPE.account = :account
 * 			And STRIPE.id = :stripeId
 * 		Order By STRIPE.id
 * ]
 * 
 * {@link StripeCustomerDao#getByAccount(Account, Long)}
 */
	@Override
	public StripeCustomer getByAccount(Long stripeId, Account account) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeCustomer> query = builder.createQuery(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
	
		query.select(root)
				.where(builder.equal(root.get(StripeCustomer_.id), stripeId)
						, builder.equal(root.get(StripeCustomer_.account), account))
				;
		return entityManager.createQuery(query).getSingleResult();
	}








/**********************************************************************************
 * Update stripe_status
 * 
 * @query
 * [
 * 		Update {@link StripeCustomer} STRIPE
 * 		Set STRIPE.status = :status
 * 		Where STRIPE = :stripe
 * ]
 * 
 * {@link StripeCustomerDao#updateStatus(StripeCustomer, StripeStatus)}
 */
	@Override
	public void updateStatus(StripeCustomer stripe, StripeStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<StripeCustomer> query = builder.createCriteriaUpdate(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		
		query.set(root.get(StripeCustomer_.status), status)
			.where(builder.equal(root, stripe));
		
		entityManager.createQuery(query).executeUpdate();
	}








/**********************************************************************************
 * Update stripe_status
 * 
 * @query
 * [
 * 		Update {@link StripeCustomer} STRIPE
 * 		Set STRIPE.status = :status
 * 		Where STRIPE = :stripe(s)
 * ]
 * 
 * {@link StripeCustomerDao#updateStatus(Listr, StripeStatus)}
 */
	@Override
	public void updateStatus(List<StripeCustomer> stripes, StripeStatus status) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<StripeCustomer> query = builder.createCriteriaUpdate(StripeCustomer.class);
		Root<StripeCustomer> root = query.from(StripeCustomer.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		for(StripeCustomer stripe : stripes) {
			predicates.add(builder.equal(root, stripe));
		}
		query.set(root.get(StripeCustomer_.status), status)
			.where(predicates.toArray(new Predicate[]{}));
		
		entityManager.createQuery(query).executeUpdate();
	}


	

}
