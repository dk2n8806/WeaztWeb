package com.core.dao.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.Shipment;
import com.common.entity.subscription.Subscription;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.OrderTransactionDaoImpl;

public interface OrderTransactionDao
extends GenericRepository<OrderTransaction, Long>{


	/** {@link OrderTransactionDaoImpl#getByMerchant(Long, Merchant)} */
	OrderTransaction getByMerchant(Long transactionId, Merchant merchant);

	/** {@link OrderTransactionDaoImpl#getByShipment(Shipment)} */
	OrderTransaction getByShipment(Shipment shipment);
	
	/** {@link OrderTransactionDaoImpl#getShipment(OrderTransaction)} */
	Shipment getShipment(OrderTransaction orderDetail);
	
	/** {@link OrderTransactionDaoImpl#getShipmentByMerchant(Long, Merchant)} */
	Shipment getShipmentByMerchant(Long transactionId, Merchant merchant);

	/** {@link OrderTransactionDaoImpl#getOrder(OrderTransaction)} */
	OrderBundle getOrder(OrderTransaction transaction);
	
	
	/** {@link OrderTransactionDaoImpl#getByOrder(Order, Pageable)} */
	List<OrderTransaction> getByOrder(OrderBundle order, Pageable pageable);

	/** {@link OrderTransactionDaoImpl#getBySubscription(Subscription, Pageable)} */
	List<OrderTransaction> getBySubscription(Subscription subscription, Pageable pageable);
	
	
	
	
	
	/** {@link OrderTransactionDaoImpl#countBySubscription(Subscription)} */
	long countBySubscription(Subscription subscription);
	
	/** {@link OrderTransactionDaoImpl#countByOrder(Order)} */
	long countByOrder(OrderBundle order);

	/** {@link OrderTransactionDaoImpl#countByOrder(Order, ShipmentStatus)} */
	long countByOrder(OrderBundle order, ShipmentStatus status);
	
	
	
	/** {@link OrderTransactionDaoImpl#countByAccount(Account, DateInterval)} */
	long countByAccount(Account account, DateInterval dateInterval);
	
	
	/** {@link OrderTransactionDaoImpl#getShipment(OrderTransaction)} */
	List<Shipment> getShipments(OrderBundle order, Pageable pageable);
	
	
	
	
}
