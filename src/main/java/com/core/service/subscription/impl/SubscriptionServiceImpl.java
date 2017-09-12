package com.core.service.subscription.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.embeded.SubscriptionRating;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.subscription.SubscriptionDao;
import com.core.service.subscription.SubscriptionService;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService
{
	
	@Autowired SubscriptionDao subscriptionDao;

	@Override
	public Subscription findById(Long id) {
		return subscriptionDao.findById(id);
	}

	@Override
	public Subscription getReference(Long id) {
		return subscriptionDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return subscriptionDao.getRowCount();
	}

	
	@Override
	public void update(Subscription subscription) {
		if(subscription != null) {
			subscriptionDao.update(subscription);
		}
	}
	

	/** :::
	 *<p>Create & save a new subscription entity</p>
	 * {@link SubscriptionService#save(Account, Product, int, int, ValuePerShipment)}
	 * ::: */
	@Override
	public Subscription save(Account account, Product product,
										int nos, int frequency, ValuePerShipment pershipment) 
	{
		Subscription subscription = Subscription.create(
							account, product, pershipment, nos, frequency);
		if(subscription != null) {
			subscriptionDao.persist(subscription);
		}
		return subscription;
	}

	
	
	/** :::
	 * <p>Retrieve a list of subscriptions by an account</p>
	 * {@link SubscriptionService#getByAccount(Account, SubscriptionStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Subscription> getByAccount(Account account
			, SubscriptionStatus status, DateInterval dateInterval,Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return subscriptionDao.getSubscriptions(account, null, status, dateInterval, pageable);
	}


	/** :::
	 * <p>Count total subscriptions by an account</p>
	 * {@link SubscriptionService#countByAccount(Account, SubscriptionStatus, DateInterval)}
	 * ::: */
	@Override
	public long countByAccount(Account account, SubscriptionStatus status, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return subscriptionDao.countSubscriptions(account, null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}


	/** :::
	 * <p>Retrieve a product by a subscription</p>
	 * {@link SubscriptionService#getProduct(Subscription)}
	 * ::: */
	@Override
	public Product getProduct(Subscription subscription) {
		try {
			return subscriptionDao.getProduct(subscription);
		} catch(NoResultException e) {
			return null;
		}
	}


	/** :::
	 * <p>Retrieve an account by a subscription</p>
	 * {@link SubscriptionService#getAccount(Subscription)}
	 * ::: */
	@Override
	public Account getAccount(Subscription subscription) {
		try {
			return subscriptionDao.getAccount(subscription);
		} catch(NoResultException e) {
			return null;
		}
	}


	/** :::
	 * <p>Retrieve a list of subscriptions</p>
	 * {@link SubscriptionService#getSubscriptions(SubscriptionStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Subscription> getSubscriptions(SubscriptionStatus status
													, DateInterval dateInterval, Pageable pageable) {
		return subscriptionDao.getSubscriptions(null,  null, status, dateInterval, pageable);
	}

	

	/** :::
	 * <p>Count total subscriptions</p>
	 * {@link SubscriptionService#countSubscriptions(SubscriptionStatus, DateInterval)}
	 * ::: */
	@Override
	public long countSubscriptions(SubscriptionStatus status, DateInterval dateInterval) {
		try {
			return subscriptionDao.countSubscriptions(null, null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** {@link SubscriptionService#getByProduct(Product, SubscriptionStatus, DateInterval, Pageable)}
	 * */
	@Override
	public List<Subscription> getByProduct(Product product, SubscriptionStatus status, DateInterval dateInterval,
			Pageable pageable) {
		if(product == null) return new ArrayList<>();
		return subscriptionDao.getSubscriptions(null, product, status, dateInterval, pageable);
	}

	
	/** {@link SubscriptionService#countByProduct(Product, SubscriptionStatus, DateInterval)}
	 * */
	@Override
	public long countByProduct(Product product, SubscriptionStatus status, DateInterval dateInterval) {
		if(product == null) return 0;
		try {
			return subscriptionDao.countSubscriptions(null, product, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	
	
	/** :::
	 * {@link SubscriptionService#update(List)}
	 */
	@Override
	public void update(List<Subscription> subscriptions) {
		if(subscriptions.size() > 0) {
			subscriptionDao.update(subscriptions);
		}
	}

	
	/** :::
	 * <p>Lookup a subscription by an account with a given id</p>
	 * 
	 * {@link SubscriptionService#getByAccount(Long, Account)}
	 */
	@Override
	public Subscription getByAccount(Long subscriptionId, Account account) {
		try {
			return subscriptionDao.getSubscription(subscriptionId, account);
		} catch(NoResultException e){
			return null;
		}
	}

	
	/** :::
	 * {@link SubscriptionService#markAsUnsubscribed(Long)}
	 */
	@Override
	public void markAsUnsubscribed(Long subscriptionId) {
		subscriptionDao.updateStatus(subscriptionId, SubscriptionStatus.UNSUBSCRIBED);
	}

	
	/**
	 * {@link SubscriptionService#markAsSubscribed(Long)}
	 */
	@Override
	public void markAsSubscribed(Long subscriptionId) {
		subscriptionDao.updateStatus(subscriptionId, SubscriptionStatus.SUBSCRIBED);
	}

	
	
	/** ::: {@link SubscriptionService#toggleFavorite} */
	@Override
	public void toggleFavorite(Long subscriptionId, Account account) {
		Subscription subscription = this.getByAccount(subscriptionId, account);
		if(subscription != null) {
			SubscriptionRating rating = subscription.getRating();
			if(rating == null) {
				rating = SubscriptionRating.create();
			}
			rating.setFavorited(!rating.isFavorited());
			subscription.setRating(rating);
			this.update(subscription);
			
		}
	}


	/** ::: {@link SubscriptionService#toggleRecommend} */
	@Override
	public void toggleRecommend(Long subscriptionId, Account account) {
		Subscription subscription = this.getByAccount(subscriptionId, account);
		if(subscription != null) {
			SubscriptionRating rating = subscription.getRating();
			if(rating == null) {
				rating = SubscriptionRating.create();
			}
			rating.setRecommended(!rating.isRecommended());
			subscription.setRating(rating);
			this.update(subscription);
		}
	}

}
