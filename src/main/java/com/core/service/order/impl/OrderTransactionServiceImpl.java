package com.core.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.Shipment;
import com.common.entity.subscription.Subscription;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.OrderTransactionDao;
import com.core.service.order.OrderTransactionService;

@Service
@Transactional
public class OrderTransactionServiceImpl implements OrderTransactionService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private OrderTransactionDao orderTransactionDao;

	@Override
	public OrderTransaction findById(Long id) {
		return orderTransactionDao.findById(id);
	}

	@Override
	public long getRowCount() {
		return orderTransactionDao.getRowCount();
	}

	@Override
	public OrderTransaction getReference(Long id) {
		return orderTransactionDao.getReference(id);
	}

	
/******************************************************
 * Retrieve a list of order_detail by order
 * 
 * {@link OrderTransactionService#getByOrder(Order, Pageable)}
 */
	@Override
	public List<OrderTransaction> getByOrder(OrderBundle order, Pageable pageable) {
		if(order == null) {
			return new ArrayList<OrderTransaction>();
		}
		return orderTransactionDao.getByOrder(order, pageable);
	}


/*****************************************
 * Count totalCharge order_detail on order
 * 
 * {@link OrderTransactionService#countByOrder(Order)}
 */
	@Override
	public long countByOrder(OrderBundle order) {
		if(order == null) {
			return 0;
		}
		try {
			return orderTransactionDao.countByOrder(order);
		}catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	
/**************************************************
 * Retrieve a list of shipment on the order
 * 
 * {@link OrderTransactionService#getShipments(Order, Pageable)}
 */
	@Override
	public List<Shipment> getShipments(OrderBundle order, Pageable pageable) {
		if(order == null) {
			return null;
		}
		return orderTransactionDao.getShipments(order, pageable);
	}

	
	
/***************************************************
 * Retrieve a shipment on the order_detail
 * 
 * {@link OrderTransactionService#getShipment(OrderTransaction)}
 */
	@Override
	public Shipment getShipment(OrderTransaction orderDetail) {
		try {
			return orderTransactionDao.getShipment(orderDetail);
		} catch(NoResultException e) {
			return null;
		}	
	}
	
	
/*************************************************
 * Retrieve a list of order_detail on subscription
 * 
 * {@link OrderTransactionService#getBySubscription(Subscription, Pageable)}
 */
	@Override
	public List<OrderTransaction> getBySubscription(Subscription subscription,
			Pageable pageable) {
		if(subscription == null) {
			return null;
		}
		return orderTransactionDao.getBySubscription(subscription, pageable);
	}
	
	
/***************************************
 * Count totalCharge order_detail on subscription
 * 
 * {@link OrderTransactionService#countBySubscription(Subscription)}
 */
	@Override
	public long countBySubscription(Subscription subscription) {
		if(subscription == null) {
			return 0;
		}
		try {
			return orderTransactionDao.countBySubscription(subscription);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
/*****************************************************
 * Lookup an order_transaction by merchant
 * 
 * {@link OrderTransactionService#getByMerchant(Long, Merchant)}
 */
	@Override
	public OrderTransaction getByMerchant(Long transactionId, Merchant merchant) {
		if(transactionId == null || merchant == null) {
			return null;
		}
		try {
			return orderTransactionDao.getByMerchant(transactionId, merchant);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
/************************************************
 * Lookup an order_shipment by merchant on order_transaction
 * 
 * {@link OrderTransactionService#getShipmentByMerchant(Long, Merchant)}
 */
	@Override
	public Shipment getShipmentByMerchant(Long transactionId, Merchant merchant) {
		if(transactionId == null || merchant == null) {
			return null;
		}
		try {
			return orderTransactionDao.getShipmentByMerchant(transactionId, merchant);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
/**************************************************
 * Count total order_transaction by order
 * 
 * {@link OrderTransactionService#countByOrder(Order, ShipmentStatus)}
 */
	@Override
	public long countByOrder(OrderBundle order, ShipmentStatus status) {
		if(order == null) {
			return 0;
		}
		try {
			return orderTransactionDao.countByOrder(order, status);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
/**************************************************
 * Lookup an order_transaction on a shipment
 * 
 * {@link OrderTransactionService#getByShipment(Shipment)}
 */
	@Override
	public OrderTransaction getByShipment(Shipment shipment) {
		if(shipment == null) {
			return null;
		}
		try {
			return orderTransactionDao.getByShipment(shipment);
		} catch(NoResultException e) {
			logger.error("[ERROR] Unable to retrieve an order_transaction"
						+ " on shipment [" + shipment.getId() + "]");
			return null;
		}
	}

	
/*********************************************************************
 * Lookup an order by an order_transaction
 * 
 * {@link OrderTransactionService#getOrder(OrderTransaction)}
 */
	@Override
	public OrderBundle getOrder(OrderTransaction transaction) {
		if(transaction == null) {
			return null;
		}
		try {
			return orderTransactionDao.getOrder(transaction);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
	
	
	/** :::
	 * <p>Count total order transaction by a given account</p>
	 * 
	 * {@link OrderTransactionService#countByAccount(Account, DateInterval)}
	 * ::: */
	@Override
	public long countByAccount(Account account, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return orderTransactionDao.countByAccount(account, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
}
