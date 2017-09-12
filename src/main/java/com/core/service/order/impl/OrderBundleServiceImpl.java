package com.core.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.product.Product;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.MerchantOrderBundleDao;
import com.core.dao.order.OrderBundleDao;
import com.core.service.order.MerchantOrderBundleService;
import com.core.service.order.OrderBundleService;

@Service
@Transactional
public class OrderBundleServiceImpl 
	implements OrderBundleService, MerchantOrderBundleService
{

	@Autowired private OrderBundleDao orderBundleDao;
	@Autowired private MerchantOrderBundleDao merchantDao;
	
	@Override
	public OrderBundle findById(Long id) {
		return orderBundleDao.findById(id);
	}

	@Override
	public OrderBundle getReference(Long id) {
		return orderBundleDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return orderBundleDao.getRowCount();
	}

	@Override
	public void update(OrderBundle orderBundle) {
		if(orderBundle != null)
			orderBundleDao.update(orderBundle);
	}

	
	
	@Override
	public OrderBundle save(Product product, List<OrderTransaction> orderTransactions) {
		OrderBundle orderBundle = OrderBundle.create(product, orderTransactions);
		if(orderBundle != null) 
			orderBundleDao.persist(orderBundle);
		return orderBundle;
	}

	
	
	
	/**
	 * {@link OrderBundleService#getOrderBundle(OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderBundle> getOrderBundle(
			OrderStatus status, DateInterval dateInterval, Pageable pageable) {
		return orderBundleDao.getOrderBundles(null, status, dateInterval, pageable);
	}

	
	
	
	
	/**
	 * {@link OrderBundleService#countOrderBundles(OrderStatus, DateInterval)}
	 */
	@Override
	public long countOrderBundles(OrderStatus status, DateInterval dateInterval) {
		try {
			return orderBundleDao.countOrderBundles(null, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	


	

	/** :::
	 * <p>Retrieve merchant owner of order_bundle</p>
	 * {@link MerchantOrderBundleService#getMerchant(OrderBundle)}
	 */
	@Override
	public Merchant getMerchant(OrderBundle orderBundle) {
		try {
			return merchantDao.getMerchant(orderBundle);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	/** :::
	 * <p>Retrieve an order bundle by a merchant</p>
	 * {@link MerchantOrderBundleService}
	 */
	@Override
	public OrderBundle getByMerchant(Long orderBundleId, Merchant merchant) {
		try {
			return merchantDao.getByMerchant(orderBundleId, merchant);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
	
	/** :::
	 * <p>Retrieve a list of order bundle by a merchant</p>
	 * {@link MerchantOrderBundleService#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderBundle> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		if(merchant == null) return new ArrayList<>();
		return merchantDao.getByMerchant(merchant, status, dateInterval, pageable);
	}

	
	
	
	
	/** :::
	 * <p>Count total order bundle by a merchant</p>
	 * {@link MerchantOrderBundleService#countByMerchant(Merchant, OrderStatus, DateInterval)}
	 */
	@Override
	public long countByMerchant(Merchant merchant, OrderStatus status, DateInterval dateInterval) {
		if(merchant == null) return 0;
		try {
			return merchantDao.countByMerchant(merchant, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** :::
	 * <p>Set new order bundle to completed</p>
	 * {@link OrderBundleService#completed(Long)}
	 */
	@Override
	public void completed(Long orderBundleId) {
		orderBundleDao.updateStatus(orderBundleId, OrderStatus.PENDING, OrderStatus.COMPLETED);
	}

	
	/** :::
	 * {@link OrderBundleService#getByProduct}
	 */
	@Override
	public List<OrderBundle> getByProduct(
			Product product, OrderStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		if(product == null) return new ArrayList<>();
		return orderBundleDao.getOrderBundles(product, status, dateInterval, pageable);
	}

	
	/** :::
	 * {@link OrderBundleService#countByProduct}
	 */
	@Override
	public long countByProduct(Product product, OrderStatus status, DateInterval dateInterval) 
	{
		if(product == null) return 0;
		return orderBundleDao.countOrderBundles(product, status, dateInterval);
	}
	
	
}
