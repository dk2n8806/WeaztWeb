package com.core.dao.subscription.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.subscription.SubscriptionDao;

@Repository
public class SubscriptionDaoImpl 
	extends GenericJpaRepository<Subscription, Long>
implements SubscriptionDao
{

	
	
	/** :::
	 * <p>Retrieve a list of of subscriptions by a account</p>
	 * 
	 * @query
	 * [
	 * 		Select S From {@link Subscription} S
	 * 		Where S.account = :account 				
	 * 			And S.product = :product						
	 * 			And S.status = :status									
	 *			And S.createdOn Between :from :to		
	 * ]
	 *
	 * {@link SubscriptionDao#getSubscriptions(Account, Product, SubscriptionStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Subscription> getSubscriptions(Account account, Product product
			, SubscriptionStatus status,
			DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
		Root<Subscription> root = query.from(Subscription.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(account != null)
			predicates.add(builder.equal(root.get(Subscription_.account), account));
		if(product != null)
			predicates.add(builder.equal(root.get(Subscription_.product), product));
		if(status != null) 
			predicates.add(builder.equal(root.get(Subscription_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(Subscription_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo() ));
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return this.getResultList(query, pageable);
	}



	
	/** :::
	 * <p>Count total subscription entities</p>
	 * 
	 * @query
	 * [
	 * 		Select count(S) From Subscription S
	 * 		Where S.account = :account 								
	 * 			And S.product = :product		
	 * 			And S.status = :status									
	 *		And S.subscribedOn Between :from And :to		
	 * ]
	 *
	 * {@link SubscriptionDao#countSubscriptions}
	 * ::: */
	@Override
	public long countSubscriptions(Account account, Product product
												, SubscriptionStatus status, DateInterval dateInterval) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Subscription> root = query.from(Subscription.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(account != null)
			predicates.add(builder.equal(root.get(Subscription_.account), account));
		if(product != null)
			predicates.add(builder.equal(root.get(Subscription_.product), product));
		if(status != null) 
			predicates.add(builder.equal(root.get(Subscription_.status), status));
		if(dateInterval != null) 
			predicates.add(builder.between(root.get(Subscription_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo() ));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}))
			;
		return entityManager.createQuery(query).getSingleResult();
	}




	
	
	/** :::
	 * <p>Retrieve a product on a subscription</p>
	 * 
	 * @query
	 * [
	 * 		Select P From {@link Subscription} S
	 * 			Join S.product P
	 * 		Where S = :subscription
	 * ]
	 * 
	 * {@link SubscriptionDao#getProduct(Subscription)}
	 * ::: */
	@Override
	public Product getProduct(Subscription subscription) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Subscription> root = query.from(Subscription.class);
		Join<Subscription, Product> joinProduct = root.join(Subscription_.product);
		query.select(joinProduct).where(builder.equal(root, subscription));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	/** ::::
	 * <p>Lookup an account of the subscription</p>
	 * @query
	 * [ 
	 * 		Select C From {@link Subscription} S
	 * 			Join S.account C
	 * 		Where S = :subscription
	 * ]
	 * 
	 * {@link SubscriptionDao#getAccount(Subscription)}
	 * 
	 * ::: */
	@Override
	public Account getAccount(Subscription subscription) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Subscription> root = query.from(Subscription.class);
		Join<Subscription, Account> joinCustomer = root.join(Subscription_.account);
		query.select(joinCustomer).where(builder.equal(root, subscription));
		return entityManager.createQuery(query).getSingleResult();
	}




	/** :::
	 * <p></p>
	 * 
	 * @query
	 * [
	 * 		Select * from {@link Subscription} S
	 * 		Where S.id = :subscriptionId
	 * 			And S.account = :account
	 * ]
	 * 
	 * {@link SubscriptionDao#getSubscription(Long, Account)}
	 */
	@Override
	public Subscription getSubscription(Long subscriptionId, Account acocunt) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
		Root<Subscription> root = query.from(Subscription.class);
		query.select(root)
				.where(builder.equal(root.get(Subscription_.id), subscriptionId)
						, builder.equal(root.get(Subscription_.account), acocunt));
		return entityManager.createQuery(query).getSingleResult();
	}




	/** :::
	 * <p>Update status of a subscription</p>
	 * 
	 * @query
	 * [
	 * 		Update {@link Subscription} S
	 * 		Set S.status = :status
	 * 		Where S.id = :subscriptionId
	 * ]
	 * 
	 * {@link SubscriptionDao#updateStatus(Long, SubscriptionStatus)}
	 */
	@Override
	public void updateStatus(Long subscriptionId, SubscriptionStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Subscription> query = builder.createCriteriaUpdate(Subscription.class);
		Root<Subscription> root = query.from(Subscription.class);
		query.set(root.get(Subscription_.status), status)
				.where(builder.equal(root.get(Subscription_.id), subscriptionId));
		entityManager.createQuery(query).executeUpdate();
	}


}
