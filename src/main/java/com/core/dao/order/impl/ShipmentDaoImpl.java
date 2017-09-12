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
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.OrderTransaction_;
import com.common.entity.order.Shipment;
import com.common.entity.order.Shipment_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.ShipmentDao;


@Repository
public class ShipmentDaoImpl 
extends GenericJpaRepository<Shipment, Long> implements ShipmentDao {

	
	@Override
	public void flush() {
		entityManager.flush();
	}
	
/**************************************************
 * Update statuc of a shipment
 * 
 * @query
 * [
 * 		Update {@link Shipment} S
 * 		Set S.status = :status
 * 		Where S = :shipment
 * ]
 * 
 * {@link ShipmentDao#updateStatus(Shipment, ShipmentStatus)}
 */
	@Override
	public void updateStatus(Shipment shipment, ShipmentStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Shipment> query = builder.createCriteriaUpdate(Shipment.class);
		Root<Shipment> root = query.from(Shipment.class);
		
		query.set(root.get(Shipment_.status), status)
			.where(builder.equal(root, shipment));
		
		entityManager.createQuery(query).executeUpdate();
	}
	
	
/************************************************************
 * Lookup a shipment by merchant
 * 
 * @query
 * [
 * 		Select SHIPMENT 
 * 		From {@link OrderTransaction} OD
 * 			Join OD.shipment SHIPMENT
 * 			Join OD.order.product PRODUCT
 * 		Where SHIPMENT.id = :shipmentId
 * 			And PRODUCT.merchant = :merchant
 * ]
 * 
 * {@link ShipmentDao#getByMerchant(Long, Merchant)}
 */
	@Override
	public Shipment getByMerchant(Long shipmentId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		Join<OrderBundle, Product> joinOrder = root.join(OrderTransaction_.orderBundle)
											.join(OrderBundle_.product);
		Join<OrderTransaction, Shipment> joinShipment = root.join(OrderTransaction_.shipment);
		
		query.select(joinShipment)
			.where(builder.equal(joinShipment.get(Shipment_.id), shipmentId)
				 , builder.equal(joinOrder.get(Product_.merchant), merchant));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	/** *
	 * {@link ShipmentDao#getShipments(ShipmentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<Shipment> getShipments(ShipmentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
		Root<Shipment> root = query.from(Shipment.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Shipment_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Shipment_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}
	
	
	/**
	 * {@link ShipmentDao#countShipment(ShipmentStatus, DateInterval)}
	 */
	@Override
	public long countShipment(ShipmentStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Shipment> root = query.from(Shipment.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Shipment_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Shipment_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
