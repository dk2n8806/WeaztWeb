package com.core.service.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.OrderException;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.impl.OrderIntentServiceImpl;

public interface MerchantOrderIntentSerivce {

	/** {@link OrderIntentServiceImpl#getByMerchant(Long, Merchant)} */
	OrderIntent getByMerchant(Long orderIntentId, Merchant merchant);
	
	
	
	/** {@link OrderIntentServiceImpl#cancelByProduct(Product)} */
	void cancelByProduct(Product product) throws OrderException;
	
	/** {@link OrderIntentServiceImpl#getByMerchant} */
	List<OrderIntent> getByMerchant(
			Merchant merchant, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentServiceImpl#getScheduledSubscriptionsByProduct(Product, OrderIntentStatus, DateInterval, Pageable)} */
	List<Subscription> getScheduledSubscriptionsByProduct(
			Product product, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderIntentServiceImpl#countByMerchant} */
	long countByMerchant(Merchant merchant
							, OrderIntentStatus status, DateInterval dateInterval);


	/** {@link OrderIntentServiceImpl#getByProduct} */
	List<OrderIntent> getByProduct(Product product, OrderIntentStatus status
																, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentServiceImpl#countByProduct} */
	long countByProduct(Product product, OrderIntentStatus status, DateInterval dateInterval);
}
