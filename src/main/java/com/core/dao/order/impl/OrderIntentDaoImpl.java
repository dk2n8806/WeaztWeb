package com.core.dao.order.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.order.OrderIntent_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.MerchantOrderIntentDao;
import com.core.dao.order.OrderIntentDao;

@Repository
public class OrderIntentDaoImpl 
	extends GenericJpaRepository<OrderIntent, Long>
implements OrderIntentDao, MerchantOrderIntentDao
{

	/** :::
	 * <p>Retrieve a total order_intent by schedule</p>
	 * 
	 *	<query>
	 *		Select count(OI) From {@link OrderIntent} OI
	 *			Join OI.subscription S
	 *			Join S.product P
	 *		Where P.merchant = :merchant
	 *			And P = :product
	 *			And OI.status = :status
	 *			And OI.scheduledDate Betwee :from And :to
	 * </query>
	 * 
	 * {@link MerchantOrderIntentDao#countScheduledOrderIntents(Merchant, Product, OrderIntentStatus, DateInterval)}
	 * ::: */
	@Override
	public long countScheduledOrderIntents(Merchant merchant, Product product, OrderIntentStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(merchant != null)  
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(product != null)
			predicates.add(builder.equal(joinProduct, product));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	

	/** :::
	 * <p>Retrieve a list of order intents</p>
	 * 
	 *	<query>
	 *		Select OI From {@link OrderIntent} OI
	 *			Join OI.subscription S
	 *			Join S.product P
	 *		Where P.merchant = :merchant
	 *			And P = :product
	 *			And OI.status = :status
	 *			And OI.scheduledDate Betwee :from And :to
	 *		Order By OI DESC
	 * </query>
	 * 
	 * {@link MerchantOrderIntentDao#getScheduleOrderIntents(Merchant, Product, OrderIntentStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<OrderIntent> getScheduleOrderIntents(Merchant merchant, Product product, OrderIntentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderIntent> query = builder.createQuery(OrderIntent.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(merchant != null)  
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(product != null)
			predicates.add(builder.equal(joinProduct, product));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
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
	 * 		Select OI From {@link OrderIntent} OI
	 * 		Where OI.subscription = :subscription
	 * 			And OI.status = :status
	 * 			And OI.createdOn Between :from And :to
	 * 		Order By OI Desc
	 * ]
	 * 
	 * {@link OrderIntentDao#getOrderIntents(Subscription, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntent> getOrderIntents(Subscription subscription
			, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) 
	{

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderIntent> query = builder.createQuery(OrderIntent.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(subscription != null)
			predicates.add(builder.equal(root.get(OrderIntent_.subscription), subscription));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
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
	 * 		Select count(OI) From {@link OrderIntent} OI
	 * 		Where OI.subscription = :subscription
	 * 			And OI.status = :status
	 * 			And OI.createdOn Between :from And :to
	 * 		Order By OI Desc
	 * ]
	 * 
	 * {@link OrderIntentDao#countOrderIntents(Subscription, OrderIntentStatus, DateInterval)}
	 */
	@Override
	public long countOrderIntents(Subscription subscription
							, OrderIntentStatus status, DateInterval dateInterval) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(subscription != null)
			predicates.add(builder.equal(root.get(OrderIntent_.subscription), subscription));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}




	/** :::
	 * 
	 * 
	 * {@link OrderIntentDao#updateStatusBySubscription(Subscription, OrderIntentStatus, OrderIntentStatus)}
	 */
	@Override
	public void updateStatusBySubscription(List<Subscription> subscriptions, OrderIntentStatus oldStatus, OrderIntentStatus newStatus) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<OrderIntent> query = builder.createCriteriaUpdate(OrderIntent.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		
		List<Predicate> predicates = new ArrayList<>();
		for(Subscription s : subscriptions) {
			predicates.add(builder.equal(root.get(OrderIntent_.subscription), s));
		}
		
		if(oldStatus != null && newStatus != null) {
			query.set(root.get(OrderIntent_.status), newStatus)
				.where(builder.or(predicates.toArray(new Predicate[]{}))
						, builder.equal(root.get(OrderIntent_.status), oldStatus));
			entityManager.createQuery(query).executeUpdate();
		}
	}





	/** :::
	 * <p>Retrieve an order_intent by a merchant with a given order_intent_id</p>
	 * 
	 * @query
	 * [
	 * 		Select OI From {@link OrderIntent} OI
	 * 			Join OI.subscription S
	 * 			Join S.product P
	 * 		Where OI.id = :orderIntentId
	 * 			And P.merchant = :merchant
	 * ]
	 * 
	 * {@link MerchantOrderIntentDao#getByMerchant(Long, Merchant)}
	 */
	@Override
	public OrderIntent getByMerchant(Long orderIntentId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderIntent> query = builder.createQuery(OrderIntent.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		query.select(root)
			.where(builder.equal(root.get(OrderIntent_.id), orderIntentId)
					, builder.equal(joinProduct.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}





	/** :::
	 * <p>Lookup a subscription entity on a given orderIntent entity</p>
	 * 
	 * @query
	 * [
	 * 		Select OI.subscription From {@link OrderIntent} OI
	 * 		Where OI = :orderIntent
	 * ]
	 * 
	 * {@link OrderIntentDao#getSubscription(OrderIntent)}
	 */
	@Override
	public Subscription getSubscription(OrderIntent orderIntent) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		query.select(root.get(OrderIntent_.subscription))
				.where(builder.equal(root, orderIntent));
	return entityManager.createQuery(query).getSingleResult();
	}





	/** :::
	 * <p>Lookup a list of subscriptions by a merchant</p>
	 * 
	 * @query
	 * [
	 *		Select OI.subscription From {@link OrderIntent} OI
	 *			Join OI.subscription S
	 *			Join S.product P
	 *		Where P.merchant = :merchant
	 *			And P = :product
	 *			And OI.status = :status
	 *			And OI.scheduledDate Between :from And :to
	 *		Order By OI DESC
	 * ]
	 * 
	 * {@link MerchantOrderIntentDao#getScheduledSubscriptions(Merchant, Product, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<Subscription> getScheduledSubscriptions(Merchant merchant, Product product, OrderIntentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(merchant != null)  
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(product != null)
			predicates.add(builder.equal(joinProduct, product));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(root.get(OrderIntent_.subscription))
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		
		if(pageable == null) {
			return entityManager.createQuery(query).getResultList();
		}
		
		return entityManager.createQuery(query)
										.setFirstResult(pageable.getOffset())
										.setMaxResults(pageable.getPageSize())
										.getResultList();
	}





	/** :::
	 * <p>Count order intent by an account</p>
	 * 
	 * @query
	 * [
	 * 		Select count(OI) From {@link OrderIntent} OI
	 * 		Join OI.subscription S
	 * 		Where S.account = :account
	 * 			And OI.status = :status
	 * 			And OI.scheduledDate Between :from And :to
	 * ]	
	 * 
	 * {@link OrderIntentDao#countScheduledByAccount(Account, OrderIntentStatus, DateInterval)}
	 */
	@Override
	public long countScheduledByAccount(Account account, OrderIntentStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(account != null)
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)	
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}





	@Override
	public void updateStatusByProduct(Product product, OrderIntentStatus oldStatus, OrderIntentStatus newStatus) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<OrderIntent> query = builder.createCriteriaUpdate(OrderIntent.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		
		
		Subquery<OrderIntent> subQuery = query.subquery(OrderIntent.class);
		Root<OrderIntent> subRoot = subQuery.from(OrderIntent.class);
		subQuery.select(subRoot);
		
		
		Join<OrderIntent, Subscription> joinSubscription = subRoot.join(OrderIntent_.subscription);
		subQuery.where(builder.equal(joinSubscription.get(Subscription_.product), product));
		
		if(oldStatus != null && newStatus != null) {
			query.set(root.get(OrderIntent_.status), newStatus)
				.where(root.in(subQuery)
						, builder.equal(root.get(OrderIntent_.status), oldStatus));
			entityManager.createQuery(query).executeUpdate();
		}
	}

}
