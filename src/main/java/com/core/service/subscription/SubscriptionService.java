package com.core.service.subscription;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.subscription.impl.SubscriptionServiceImpl;

public interface SubscriptionService extends GenericService<Subscription, Long>{

	/** :::
	 * Create & save a new subscription entity
	 * {@link SubscriptionServiceImpl#save(Account, Product, int, int, ValuePerShipment)}
	 * ::: */
	Subscription save(Account account, Product product
							, int nos, int frequency, ValuePerShipment pershipment);
	
	
	/** {@link SubscriptionServiceImpl#getByAccount(Long, Account)} */
	Subscription getByAccount(Long subscriptionId, Account account);
	
	
	/** {@link SubscriptionServiceImpl#markAsUnsubscribed(Long)} */
	void markAsUnsubscribed(Long subscriptionId);
	
	/** {@link SubscriptionServiceImpl#markAsSubscribed(Long)} */
	void markAsSubscribed(Long subscriptionId);
	
	
	
	
	/** {@link SubscriptionServiceImpl#update(Subscription)} */
	void update(Subscription subscription);
	
	/** {@link SubscriptionServiceImpl#update(List)} */
	void update(List<Subscription> subscriptions);
	
	
	/** :::
	 * Retrieve product by the subscription
	 * {@link SubscriptionServiceImpl#getProduct(Subscription)}
	 * ::: */
	Product getProduct(Subscription subscription);
	
	
	/** :::
	 * Retrieve account by the subscription
	 * {@link SubscriptionServiceImpl#getAccount(Subscription)}
	 * ::: */
	Account getAccount(Subscription subscription);
	
	
	/** :::
	 * Retrieve a list of subscriptions
	 * {@link SubscriptionServiceImpl#getSubscriptions(SubscriptionStatus, DateInterval, Pageable)}
	 * ::: */
	List<Subscription> getSubscriptions(SubscriptionStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** :::
	 * Retrieve a list of subscriptions by an account
	 * {@link SubscriptionServiceImpl#getByAccount(Account, SubscriptionStatus, DateInterval, Pageable)}
	 * ::: */
	List<Subscription> getByAccount(Account account, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link SubscriptionServiceImpl#getByProduct(Product, SubscriptionStatus, DateInterval, Pageable)} */
	List<Subscription> getByProduct(Product product, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link SubscriptionServiceImpl#countByProduct(Product, SubscriptionStatus, DateInterval)} */
	long countByProduct(Product product, SubscriptionStatus status, DateInterval dateInterval);
	
	
	
	/** :::
	 * Count total subscriptions by an account
	 * {@link SubscriptionServiceImpl#countByAccount(Account, SubscriptionStatus, DateInterval)}
	 * ::: */
	long countByAccount(Account account, SubscriptionStatus status, DateInterval dateInterval);
	
	
	/** :::
	 * Count total subscriptions
	 * {@link SubscriptionServiceImpl#countSubscriptions(SubscriptionStatus, DateInterval)} 
	 * ::: */
	long countSubscriptions(SubscriptionStatus status, DateInterval dateInterval);
	
	
	
	
	void toggleFavorite(Long subscriptionId, Account account);
	void toggleRecommend(Long subscriptionId, Account account);
}
