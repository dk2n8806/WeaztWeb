package com.core.dao.subscription;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.subscription.impl.SubscriptionDaoImpl;

public interface SubscriptionDao 
	extends GenericRepository<Subscription, Long>
{
	
	/** {@link SubscriptionDaoImpl#updateStatus(Long, SubscriptionStatus)} */
	void updateStatus(Long subscriptionId, SubscriptionStatus status);

	
	/** {@link SubscriptionDaoImpl#getSubscription(Long, Account)} */
	Subscription getSubscription(Long subscriptionId, Account acocunt);
	
	
	/** {@link SubscriptionDaoImpl#getProduct(Subscription)} */
	Product getProduct(Subscription subscription);
	
	/** {@link SubscriptionDaoImpl#getAccount(Subscription)} */
	Account getAccount(Subscription subscription);
	

	
	/** {@link SubscriptionDaoImpl#countSubscriptions} */
	long countSubscriptions(Account account, Product product, SubscriptionStatus status, DateInterval dateInterval);
	
	/** {@link SubscriptionDaoImpl#getSubscriptions} */
	List<Subscription> getSubscriptions(Account account, Product product, SubscriptionStatus status
																	, DateInterval dateInterval, Pageable pageable);
	

}
