package com.core.service.order;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.order.OrderIntent;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.order.impl.OrderIntentServiceImpl;

public interface OrderIntentService extends GenericService<OrderIntent,	 Long>
{

	void save(List<OrderIntent> orderIntents);
	
	
	/** {@link OrderIntentServiceImpl#save(Subscription, Date)} */
	OrderIntent save(Subscription subscription, Date scheduledFor);
	
	/** {@link OrderIntentServiceImpl#getSubscription(OrderIntent)} */
	Subscription getSubscription(OrderIntent orderIntent);
	
	/** {@link OrderIntentServiceImpl#getList()} */
	List<OrderIntent> getList();
	
	/** {@link OrderIntentServiceImpl#getOrderIntents(OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntent> getOrderIntents(OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderIntentServiceImpl#countOrderIntents(OrderIntentStatus, DateInterval)} */
	long countOrderIntents(OrderIntentStatus status, DateInterval dateInterval);
	
	
	/** {@link OrderIntentServiceImpl#getBySubscription(Subscription, OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntent> getBySubscription(Subscription subscription
			, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentServiceImpl#countBySubscription(Subscription, OrderIntentStatus, DateInterval)} */
	long countBySubscription(Subscription subscription
					, OrderIntentStatus status, DateInterval dateInterval);
	
	
	/** {@link OrderIntentServiceImpl#countScheduledByAccount(Account, DateInterval)} */
	long countScheduledByAccount(Account account, DateInterval dateInterval);
	
	
	/** {@link OrderIntentServiceImpl#update(List)} */
	void update(List<OrderIntent> orders);
	


	/** {@link OrderIntentServiceImpl#completeBySubscription(Subscription)} */
	void completeBySubscription(Subscription subscription);
	
	/** {@link OrderIntentServiceImpl#completeBySubscriptions(List)} */
	void completeBySubscriptions(List<Subscription> subscriptions);
	
	
	/** {@link OrderIntentServiceImpl#cancelBySubscription(Subscription)} */
	void cancelBySubscription(Subscription subscription);
}
