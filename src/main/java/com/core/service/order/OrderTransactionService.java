package com.core.service.order;

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
import com.core.service.GenericService;
import com.core.service.order.impl.OrderTransactionServiceImpl;

public interface OrderTransactionService 
extends GenericService<OrderTransaction, Long>{


	/** {@link OrderTransactionServiceImpl#getByMerchant(Long, Merchant)} */
	OrderTransaction getByMerchant(Long transactionId, Merchant merchant);
	
	
	/** {@link OrderTransactionServiceImpl#getByShipment(Shipment)} */
	OrderTransaction getByShipment(Shipment shipment);
	
	
	/** {@link OrderTransactionServiceImpl#getShipmentByMerchant(Long, Merchant)} */
	Shipment getShipmentByMerchant(Long transactionId, Merchant merchant);

	
	/** {@link OrderTransactionServiceImpl#getShipment(OrderTransaction)} */
	Shipment getShipment(OrderTransaction transaction);
		
	
	/** {@link OrderTransactionServiceImpl#getOrder(OrderTransaction)} */
	OrderBundle getOrder(OrderTransaction transaction);
	
	
	/** {@link OrderTransactionServiceImpl#getByOrder(Order, Pageable)} */
	List<OrderTransaction> getByOrder(OrderBundle order, Pageable pageable);
	
	
	/** {@link OrderTransactionServiceImpl#getBySubscription(Subscription, Pageable)}*/
	List<OrderTransaction> getBySubscription(Subscription subscription, Pageable pageable);
	
	
	/** {@link OrderTransactionServiceImpl#getShipment(OrderTransaction)} */
	List<Shipment> getShipments(OrderBundle order, Pageable pageable);
	
	
	/** {@link OrderTransactionServiceImpl#countByOrder(Order, ShipmentStatus)} */
	long countByOrder(OrderBundle order, ShipmentStatus status);

	
	long countBySubscription(Subscription subscription);

	
	/** {@link OrderTransactionServiceImpl#countByOrder(Order)} */
	long countByOrder(OrderBundle order);
	
	
	/** {@link OrderTransactionServiceImpl#countByAccount(Account, DateInterval)} */
	long countByAccount(Account account, DateInterval dateInterval);

}
