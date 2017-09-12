package com.core.dao.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.order.OrderBundle;
import com.common.entity.product.Product;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.OrderBundleDaoImpl;

public interface OrderBundleDao extends GenericRepository<OrderBundle, Long> {

	void updateStatus(Long orderBundleId, OrderStatus curr, OrderStatus update);
	
	/** {@link OrderBundleDaoImpl#getOrderBundles} */
	List<OrderBundle> getOrderBundles(Product product, OrderStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderBundleDaoImpl#countOrderBundles} */
	long countOrderBundles(Product product, OrderStatus status, DateInterval dateInterval);
}
