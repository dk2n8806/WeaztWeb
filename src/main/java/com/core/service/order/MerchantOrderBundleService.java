package com.core.service.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.impl.OrderBundleServiceImpl;

/**************************
 * 
 * @author dk
 *
 */
public interface MerchantOrderBundleService {

	/** {@link OrderBundleServiceImpl#getMerchant(OrderBundle)} */
	Merchant getMerchant(OrderBundle order);
	
	/** {@link OrderBundleServiceImpl#getByMerchant(Long, Merchant)} */
	OrderBundle getByMerchant(Long orderBundleId, Merchant merchant);

	/** {@link OrderBundleServiceImpl#getByMerchant(Long, Merchant)}  */
	List<OrderBundle> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderBundleServiceImpl#countByMerchant(Merchant, OrderBundleStatus)} */
	long countByMerchant(Merchant merchant, OrderStatus status, DateInterval dateInterval);
	
}
