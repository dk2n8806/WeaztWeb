package com.core.dao.order.impl;

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

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderBundle_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.MerchantOrderBundleDao;
import com.core.dao.order.OrderBundleDao;

@Repository
public class OrderBundleDaoImpl 
	extends GenericJpaRepository<OrderBundle, Long>
implements OrderBundleDao, MerchantOrderBundleDao
{

	
	/** 
	 * {@link OrderBundleDao#getOrderBundles(OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderBundle> getOrderBundles(
			Product product, OrderStatus status, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundle> query = builder.createQuery(OrderBundle.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(product != null) 
			predicates.add(builder.equal(root.get(OrderBundle_.product), product));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderBundle_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderBundle_.createdOn)
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
	
		return getResultList(query, pageable);
	}

	
	
	/**
	 * {@link OrderBundleDao#countOrderBundles}
	 */
	@Override
	public long countOrderBundles(
			Product product, OrderStatus status, DateInterval dateInterval) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(product != null) 
			predicates.add(builder.equal(root.get(OrderBundle_.product), product));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderBundle_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderBundle_.createdOn)
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
	
		return entityManager.createQuery(query).getSingleResult();
	}



	
	/**  :::
	 * <p>Retrieve merchant owner of an order</p>
	 * 
	 * @query
	 * [
	 * 		Select P.merchant From {@link OrderBundle} O
	 * 		Join O.product P
	 * 		Where O = :orderBundle
	 * ]
	 * 
	 * {@link MerchantOrderBundleDao#getMerchant(OrderBundle)}
	 */
	@Override
	public Merchant getMerchant(OrderBundle orderBundle) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> query = builder.createQuery(Merchant.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		
		query.select(joinProduct.get(Product_.merchant))
			.where(builder.equal(root, orderBundle));
		return entityManager.createQuery(query).getSingleResult();
	}



	/** :::
	 * <p>Retrieve a list of order_bundle by a merchant</p>
	 * 
	 * @query
	 * [
	 * 		Select O From {@link OrderBundle} O
	 * 		Join O.product P
	 * 		Where p.merchant = :merchant
	 * 			And O.status = :status
	 * 			And O.createdOn Between :from And :to
	 * 		Order By O desc
	 * ]
	 * 
	 * {@link MerchantOrderBundleDao#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderBundle> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundle> query = builder.createQuery(OrderBundle.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderBundle_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderBundle_.createdOn)
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}



	/** :::
	 * <p>Count total order_bundle by a merchant</p>
	 * 
	 * @query
	 * [
	 * 		Select count(O) From {@link OrderBundle} O
	 * 		Join O.product P
	 * 		Where p.merchant = :merchant
	 * 			And O.status = :status
	 * 			And O.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link MerchantOrderBundleDao#countByMerchant(Merchant, OrderStatus, DateInterval)}
	 */
	@Override
	public long countByMerchant(Merchant merchant, OrderStatus status, DateInterval dateInterval) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderBundle_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderBundle_.createdOn)
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}




	/** :::
	 * <p>Retrieve order bundle entity by a merchant</p>
	 * 
	 * @query
	 * [
	 * 		Select O From {@link OrderBundle} O
	 * 		Join O.product P
	 * 		Where p.merchant = :merchant
	 * 			And O.id = :orderBundleId
	 * ]
	 * 
	 * {@link MerchantOrderBundleDao#getByMerchant(Long, Merchant)}
	 */
	@Override
	public OrderBundle getByMerchant(Long orderBundleId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundle> query = builder.createQuery(OrderBundle.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		
		query.select(root)
				.where(builder.equal(root.get(OrderBundle_.id), orderBundleId)
						, builder.equal(joinProduct.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}



	/** :::
	 * <p>Update order bundle status</p>
	 * 
	 * @query
	 * [
	 * 		Update {@link OrderBundle} OB
	 * 		Set OB.status = :update
	 * 		Where OB.id = :orderBundleId
	 * 			And OB.status = :curr
	 * ]
	 * 
	 * {@link OrderBundleDao#updateStatus(Long, OrderStatus, OrderStatus)}
	 */
	@Override
	public void updateStatus(Long orderBundleId, OrderStatus curr, OrderStatus update) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<OrderBundle> query = builder.createCriteriaUpdate(OrderBundle.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		
		query.set(root.get(OrderBundle_.status), update)
			.where(builder.equal(root.get(OrderBundle_.id), orderBundleId)
					, builder.equal(root.get(OrderBundle_.status), curr));
		entityManager.createQuery(query).executeUpdate();
		
	}

}
