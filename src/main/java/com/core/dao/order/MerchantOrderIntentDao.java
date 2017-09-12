package com.core.dao.order;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.impl.OrderIntentDaoImpl;

/**
 * <p>A DAO that helps merchants to access to order_intent table</p>
 * 
 * @author dk2n_
 *
 */
public interface MerchantOrderIntentDao {

	/** {@link OrderIntentDaoImpl#updateStatusByProduct} */
	void updateStatusByProduct(Product product, OrderIntentStatus oldStatus, OrderIntentStatus newStatus);

	/** {@link OrderIntentDaoImpl#getScheduledSubscriptions(Merchant, Product, OrderIntentStatus, DateInterval, Pageable)} */
	List<Subscription> getScheduledSubscriptions(
			Merchant merchant, Product product
			, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentDaoImpl#getByMerchant(Long, Merchant)} */
	OrderIntent getByMerchant(Long orderIntentId, Merchant merchant);
	
	/** {@link OrderIntentDaoImpl#countScheduledOrderIntents(Merchant, Product, OrderIntentStatus, DateInterval)} */
	long countScheduledOrderIntents(Merchant merchant, Product product, OrderIntentStatus status, DateInterval dateInterval);

	
	/** {@link OrderIntentDaoImpl#getScheduleOrderIntents(Merchant, Product, OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntent> getScheduleOrderIntents(Merchant merchant, Product product
								, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);


}
