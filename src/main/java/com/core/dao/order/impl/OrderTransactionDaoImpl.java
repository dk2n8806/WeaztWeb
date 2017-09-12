package com.core.dao.order.impl;

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
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderBundle_;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.OrderTransaction_;
import com.common.entity.order.Shipment;
import com.common.entity.order.Shipment_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.OrderTransactionDao;

@Repository
public class OrderTransactionDaoImpl 
extends GenericJpaRepository<OrderTransaction, Long>
implements OrderTransactionDao
{

	
/****************************************************************
 * Retrieve a list of order_detail on an order
 * 
 * @query
 * [
 * 		Select OT 
 * 		From {@link OrderTransaction} OT
 * 		Where OT.order = :orderBundle
 * 		Order By OT.orderedOn DESC
 * ]
 * 
 * {@link OrderTransactionDao#getByOrder(Order, Pageable)}
 */
	@Override
	public List<OrderTransaction> getByOrder(OrderBundle order, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderTransaction> query = builder.createQuery(OrderTransaction.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(root)
			.where(builder.equal(root.get(OrderTransaction_.orderBundle), order))
			.orderBy(builder.desc(root.get(OrderTransaction_.createdOn)));
		
		return this.getResultList(query, pageable);
	}

	
	

/****************************************************************
 * Retrieve a list of shipment on order
 * 
 * @query
 * [
 * 		Select OT.shipment 
 * 		From {@link OrderTransactionDao} OT	
 * 		Where OT.order = :orderBundle
 * 		Order By OT.id DESC
 * ]
 * 
 * {@link OrderTransactionDao#getShipments(Order, Pageable)}
 */
	@Override
	public List<Shipment> getShipments(OrderBundle order, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(root.join(OrderTransaction_.shipment))
			.where(builder.equal(root.get(OrderTransaction_.orderBundle), order))
			.orderBy(builder.desc(root.get(OrderTransaction_.id)));
		if(pageable != null) {
			return entityManager.createQuery(query)
								.setFirstResult(pageable.getOffset())
								.setMaxResults(pageable.getPageSize())
								.getResultList();
		}
		else {
			return entityManager.createQuery(query).getResultList();
		}
	}


	
/*************************************************************************
 * Retrieve a shipment on a order_detail
 * 
 * @query
 * [
 * 		Select OT.shipment 
 * 		From {@link OrderTransaction} OT
 * 		Where OT = :orderDetail
 * ]
 * 
 * {@link OrderTransactionDao#getShipment(OrderTransaction)}
 */
	@Override
	public Shipment getShipment(OrderTransaction orderDetail) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(root.join(OrderTransaction_.shipment))
			.where(builder.equal(root, orderDetail));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
/*********************************************************************
 * Count totalCharge order_detail by order
 * 
 * @query
 * [
 * 		Select count(OT) 
 * 		From {@link OrderTransaction} OT
 * 		Where OT.order = :orderBundle
 * ]
 * 
 * {@link OrderTransactionDao#countByOrder(Order)}
 */
	@Override
	public long countByOrder(OrderBundle order) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(builder.count(root))
			.where(builder.equal(root.get(OrderTransaction_.orderBundle), order));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	

/****************************************************************
 * Retrieve a list of order_detail on an order
 * 
 * @query
 * [
 * 		Select OT 
 * 		From {@link OrderTransaction} OT
 * 		Where OT.subscription = :subscription
 * 		Order By OT.orderedOn DESC
 * ]
 * 
 * {@link OrderTransactionDao#getBySubscription(Subscription, Pageable)}
 */
	@Override
	public List<OrderTransaction> getBySubscription(Subscription subscription,
			Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderTransaction> query = builder.createQuery(OrderTransaction.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(root)
			.where(builder.equal(root.get(OrderTransaction_.subscription), subscription))
			.orderBy(builder.desc(root.get(OrderTransaction_.createdOn)));
		
		return this.getResultList(query, pageable);
	}
	
	
	

/*********************************************************************
 * Count totalCharge order_detail by order
 * 
 * @query
 * [
 * 		Select count(OT) 
 * 		From {@link OrderTransaction} OT
 * 		Where OT.subscription = :subscription
 * ]
 * 
 * {@link OrderTransactionDaoImpl#countBySubscription(Subscription)}
 */
	@Override
	public long countBySubscription(Subscription subscription) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(builder.count(root))
			.where(builder.equal(root.get(OrderTransaction_.subscription), subscription));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
/******************************************************
 * Lookup an order_transaction by merchant
 * 
 * @query
 * [
 * 		Select OT
 * 		From {@link OrderTransaction} OT
 * 			Join OT.orderBundle O
 * 			Join O.product P
 * 		Where OT.id = :transactionId
 * 			And P.merchant = :merchant
 * ]
 * 
 * {@link OrderTransactionDao#getByMerchant(Long, Merchant)}
 */
	@Override
	public OrderTransaction getByMerchant(Long transactionId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderTransaction> query = builder.createQuery(OrderTransaction.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		Join<OrderTransaction, OrderBundle> joinOrder = root.join(OrderTransaction_.orderBundle);
		Join<OrderBundle, Product> joinProduct = joinOrder.join(OrderBundle_.product);
		
		query.select(root)
				.where(builder.equal(root.get(OrderTransaction_.id), transactionId)
					 , builder.equal(joinProduct.get(Product_.merchant), merchant));
		
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	

/******************************************************
 * Lookup an order_shipment by merchant
 * 
 * @query
 * [
 * 		Select OT.shipment
 * 		From {@link OrderTransaction} OT
 * 			Join OT.orderBundle O
 * 			Join O.product P
 * 		Where OT.id = :transactionId
 * 			And P.merchant = :merchant
 * ]
 * 
 * {@link OrderTransactionDao#getByMerchant(Long, Merchant)}
 */
	@Override
	public Shipment getShipmentByMerchant(Long transactionId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		Join<OrderTransaction, OrderBundle> joinOrder = root.join(OrderTransaction_.orderBundle);
		Join<OrderBundle, Product> joinProduct = joinOrder.join(OrderBundle_.product);
		
		query.select(root.get(OrderTransaction_.shipment))
				.where(builder.equal(root.get(OrderTransaction_.id), transactionId)
					 , builder.equal(joinProduct.get(Product_.merchant), merchant));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	

/*********************************************************************
 * Count total transaction by shipment_status
 * @query
 * [	
 * 		Select count(OT) From {@link OrderTransaction}
 * 		Where OT.orderBundle = :order
 * 			And OT.shipment.status = :shipmentStatus
 * ]
 * 
 * {@link OrderTransactionDao#countByOrder(Order, ShipmentStatus)}
 */
	@Override
	public long countByOrder(OrderBundle order, ShipmentStatus shipmentStatus) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get(OrderTransaction_.orderBundle), order));
		
		if(shipmentStatus != null) {
			Join<OrderTransaction, Shipment> joinShipment 
						= root.join(OrderTransaction_.shipment);
			predicates.add(builder.equal(joinShipment.get(Shipment_.status), shipmentStatus));
		}
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}



/*****************************************************************
 * Lookup a order_transaction on a shipment
 * 
 * @query
 * [
 * 		Select OT From {@link OrderTransaction}
 * 		Where OT.shipment  = :shipment
 * ]
 * 
 * {@link OrderTransactionDao#getByShipment(Shipment)}
 */
	@Override
	public OrderTransaction getByShipment(Shipment shipment) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderTransaction> query = builder.createQuery(OrderTransaction.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		query.select(root)
				.where(builder.equal(root.get(OrderTransaction_.shipment), shipment));
		return entityManager.createQuery(query).getSingleResult();
	}


	
/****************************************************************************
 * Lookup order by an order_transaction
 * 
 * @query
 * [
 * 		Select OT.orderBundle From {@link OrderTransaction} OT
 * 		Where OT = :transaction
 * ]
 * 
 * {@link OrderTransactionDao#getOrder(OrderTransaction)}
 */
	@Override
	public OrderBundle getOrder(OrderTransaction transaction) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundle> query = builder.createQuery(OrderBundle.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		query.select(root.get(OrderTransaction_.orderBundle))
				.where(builder.equal(root, transaction));
		return entityManager.createQuery(query).getSingleResult();	
	}




	/** :::
	 * <p>Count total order transaction by an account</p>
	 * 
	 * @query
	 * [
	 * 		Select count(OT) From {@link OrderTransaction} OT
	 * 			Join OT.subscription S
	 * 		Where S.account = :account
	 * ]
	 * 
	 * {@link OrderTransactionDao#countByAccount(Account, DateInterval)}
	 */
	@Override
	public long countByAccount(Account account, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(root.get(OrderTransaction_.subscription)
																.get(Subscription_.account), account));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderTransaction_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();	
	}
					
	




}
