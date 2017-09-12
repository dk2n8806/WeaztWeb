package com.core.dao.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.order.OrderIntent;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.OrderIntentDaoImpl;

public interface OrderIntentDao extends GenericRepository<OrderIntent, Long>
{
	
	/** {@link OrderIntentDaoImpl#getSubscription(OrderIntent)} */
	Subscription getSubscription(OrderIntent orderIntent);

	/** {@link OrderIntentDaoImpl#updateStatusBySubscription(List, OrderIntentStatus, OrderIntentStatus)} */
	void updateStatusBySubscription(List<Subscription> subscriptions, OrderIntentStatus oldStatus, OrderIntentStatus newStatus);
	
	/** {@link OrderIntentDaoImpl#getOrderIntents(Subscription, OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntent> getOrderIntents(Subscription subscription, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderIntentDaoImpl#countOrderIntents(Subscription, OrderIntentStatus, DateInterval)} */
	long countOrderIntents(Subscription subscription, OrderIntentStatus status, DateInterval dateInterval);
	
	
	/** {@link OrderIntentDaoImpl#countScheduledByAccount(Account, OrderIntentStatus, DateInterval)} */
	long countScheduledByAccount(Account account, OrderIntentStatus status, DateInterval dateInterval);
}
