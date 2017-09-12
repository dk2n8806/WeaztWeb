package com.core.service.order.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.OrderException;
import com.common.type.OrderIntentStatus;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.MerchantOrderIntentDao;
import com.core.dao.order.OrderIntentDao;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderIntentService;

@Service
@Transactional
public class OrderIntentServiceImpl 
	implements OrderIntentService, MerchantOrderIntentSerivce
{

	@Autowired private OrderIntentDao orderDao;
	@Autowired private MerchantOrderIntentDao merchantDao;
	
	
	@Override
	public OrderIntent findById(Long id) {
		return orderDao.findById(id);
	}

	@Override
	public OrderIntent getReference(Long id) {
		return orderDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return orderDao.getRowCount();
	}
	
	@Override
	public List<OrderIntent> getList() {
		return orderDao.getList();
	}

	
	@Override
	public void update(List<OrderIntent> orders) {
		if(orders.size() > 0) {
			orderDao.update(orders);
		}
	}
	
	
	
	/** :::
	 * <p>Create & save a new order_intent entity</p>
	 * 
	 * @return order_intent entity
	 * {@link OrderIntentService#save(Subscription, Date)}
	 */
	@Override
	public OrderIntent save(Subscription subscription, Date scheduledDate) {
		OrderIntent orderIntent = OrderIntent.create(subscription, scheduledDate);
		if(orderIntent != null) {
			orderDao.persist(orderIntent);
		}
		return orderIntent;
	}

	
	/** :::
	 * 
	 * {@link OrderIntentService#save(List)}
	 */
	@Override
	public void save(List<OrderIntent> orderIntents) {
		if(orderIntents.size() > 0)
			orderDao.persist(orderIntents);
	}
	
	
	
	/** :::
	 * <p>Count total order_intents by a merchant</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#countByMerchant(Merchant, OrderIntentStatus, DateInterval)}
	 * ::: */
	@Override
	public long countByMerchant(Merchant merchant, OrderIntentStatus status, DateInterval dateInterval) {
		if(merchant == null) return 0;
		try {
			Product product = null;
			return merchantDao.countScheduledOrderIntents(merchant, product , status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}
	

	/** :::
	 * <p>Retrieve a list of order_intent by the merchant</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#getByMerchant(Merchant, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntent> getByMerchant(Merchant merchant, OrderIntentStatus status, DateInterval dateInterval,
			Pageable pageable) {
		if(merchant == null)  return new ArrayList<>();
		Product product = null;
		return merchantDao.getScheduleOrderIntents(merchant, product, status, dateInterval, pageable);
	}

	
	
	
	/** :::
	 * <p>Retrieve a list of order_intent by the product</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#getByProduct(Product, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntent> getByProduct(Product product, OrderIntentStatus status, DateInterval dateInterval,
			Pageable pageable) {
		if(product == null) return new ArrayList<>();
		return merchantDao.getScheduleOrderIntents(null, product, status, dateInterval, pageable);
	}

	
	/** :::
	 * <p>Count total order_intent by the product</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#countByProduct(Product, OrderIntentStatus, DateInterval)}
	 */
	@Override
	public long countByProduct(Product product, OrderIntentStatus status, DateInterval dateInterval) {
		if(product == null) return 0;
		try {
			return merchantDao.countScheduledOrderIntents(null, product, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** 
	 * <p>Retrieve a list of order_intent by a subscription</p>
	 * {@link OrderIntentService#getBySubscription(Subscription, OrderIntentStatus, DateInterval, Pageable)}
	 * */
	@Override
	public List<OrderIntent> getBySubscription(
			Subscription subscription, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		if(subscription == null) return new ArrayList<>();
		return orderDao.getOrderIntents(subscription, status, dateInterval, pageable);
	}

	
	/** 
	 * <p>Count total order_intent by a subscription</p>
	 * {@link OrderIntentService#countBySubscription(Subscription, OrderIntentStatus, DateInterval)}
	 */
	@Override
	public long countBySubscription(Subscription subscription, OrderIntentStatus status, DateInterval dateInterval) {
		if(subscription == null) return 0;
		try {
			return orderDao.countOrderIntents(subscription, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
		
	}

	
	
	

	
	/** 
	 * {@link OrderIntentService#markAsCompleteBySubscription}
	 */
	@Override
	public void completeBySubscription(Subscription subscription) {
		if(subscription != null) 
			orderDao.updateStatusBySubscription(Arrays.asList(subscription)
					, OrderIntentStatus.REQUESTING, OrderIntentStatus.COMPLETED);
	}
	
	
	/** :::
	 * <p>Update requesting order_intent to completed.</p>
	 * 
	 * {@link OrderIntentServiceImpl#completeBySubscriptions(List)}
	 */
	@Override
	public void completeBySubscriptions(List<Subscription> subscriptions) {
		if(subscriptions.size() > 0) 
			orderDao.updateStatusBySubscription(subscriptions
					, OrderIntentStatus.REQUESTING, OrderIntentStatus.COMPLETED);
	}

	
	/** 
	 * {@link OrderIntentService#markAsCancelBySubscription}
	 * */
	@Override
	public void cancelBySubscription(Subscription subscription) {
		if(subscription != null) 
			orderDao.updateStatusBySubscription(Arrays.asList(subscription)
					, OrderIntentStatus.REQUESTING, OrderIntentStatus.CANCELED);
		
	}

	
	/** :::
	 * <p>Retrieve an orderIntent by a merchant on a given order_intent_id</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#getByMerchant(Long, Merchant)}
	 */
	@Override
	public OrderIntent getByMerchant(Long orderIntentId, Merchant merchant) {
		if(merchant == null) return null;
		try { return merchantDao.getByMerchant(orderIntentId, merchant); }
		catch(NoResultException e) { return null; }
	}

	
	/** ::: 
	 * <p>Retrieve a subscription by a given orderIntent</p>
	 * 
	 * {@link OrderIntentService#getSubscription(OrderIntent)}
	 * */
	@Override
	public Subscription getSubscription(OrderIntent orderIntent) {
		if(orderIntent == null) return null;
		try { return orderIntent.getSubscription(); }
		catch(NoResultException e) { return null; }
	}

	
	/** :::
	 * <p>Retrieve a list of order subscriptions by a product</p>
	 * 
	 * {@link MerchantOrderIntentSerivce#getScheduledSubscriptionsByProduct(Product, OrderIntentStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Subscription> getScheduledSubscriptionsByProduct(Product product, OrderIntentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		if(product == null) return new ArrayList<>();
		return merchantDao.getScheduledSubscriptions(null, product, status, dateInterval, pageable);
	}

	
	/**
	 * <p>Retrieve a list of order intent entities</p>
	 * 
	 * {@link OrderIntentService#getOrderIntents(OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntent> getOrderIntents(OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) {
		return orderDao.getOrderIntents(null, status, dateInterval, pageable);
	}

	
	/**
	 * <p>Count order intent entities</p>
	 * 
	 * {@link OrderIntentService#countOrderIntents(OrderIntentStatus, DateInterval)}
	 */
	@Override
	public long countOrderIntents(OrderIntentStatus status, DateInterval dateInterval) {
		try {
			return orderDao.countOrderIntents(null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	/** :::
	 * <p>Count scheduled order intent by an account</p>
	 * 
	 * {@link OrderIntentService#countScheduledByAccount(Account, DateInterval)}
	 */
	@Override
	public long countScheduledByAccount(Account account, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return orderDao.countScheduledByAccount(account, OrderIntentStatus.REQUESTING, dateInterval);
		} catch(NoResultException e){
			return 0;
		}
	}

	
	
	
	/**
	 * Cancel order_intent by product
	 * 
	 * {@link MerchantOrderIntentSerivce#cancelByProduct(Product)}
	 */
	@Override
	public void cancelByProduct(Product product) throws OrderException {
		if(product.getStatus().equals(ProductStatus.DELETED))
			throw new OrderException("invalid product state - Already marked as DELETED");
		merchantDao.updateStatusByProduct(product, OrderIntentStatus.REQUESTING	
																				, OrderIntentStatus.CANCELED);
	}

}
