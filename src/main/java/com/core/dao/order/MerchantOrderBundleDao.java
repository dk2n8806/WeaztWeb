package com.core.dao.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.OrderBundleDaoImpl;

public interface MerchantOrderBundleDao extends GenericRepository<OrderBundle, Long>{

	/** {@link OrderBundleDaoImpl#getMerchant(OrderBundle)} */
	Merchant getMerchant(OrderBundle orderBundle);
	
	/** {@link OrderBundleDaoImpl#getByMerchant(Long, Merchant)} */
	OrderBundle getByMerchant(Long orderBundleId, Merchant merchant);
	
	/** {@link OrderBundleDaoImpl#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)}*/
	List<OrderBundle> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderBundleDaoImpl#countByMerchant(Merchant, OrderStatus, DateInterval)} */
	long countByMerchant(Merchant merchant, OrderStatus status, DateInterval dateInterval);
}
