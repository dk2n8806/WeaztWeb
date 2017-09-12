package com.core.service.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.product.Product;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.order.impl.OrderBundleServiceImpl;

public interface OrderBundleService extends GenericService<OrderBundle, Long>{

	/** {@link OrderBundleServiceImpl#save(Product, List)} */
	OrderBundle save(Product product, List<OrderTransaction> orderTransactions);
	
	@Deprecated void update(OrderBundle orderBundle);
	
	/** {@link OrderBundleServiceImpl#completed(Long)} */
	void completed(Long orderBundleId);
	
	/** {@link OrderBundleServiceImpl#getOrderBundle(OrderStatus, DateInterval, Pageable)} */
	List<OrderBundle> getOrderBundle(OrderStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderBundleServiceImpl#getByProduct} */
	List<OrderBundle> getByProduct(Product product, OrderStatus status, DateInterval dateInterval, Pageable pageable);

	/** {@link OrderBundleServiceImpl#countByProduct} */
	long countByProduct(Product product, OrderStatus status, DateInterval dateInterval);
	
	/** {@link OrderBundleServiceImpl#countOrderBundles(OrderStatus, DateInterval)} */
	long countOrderBundles(OrderStatus status, DateInterval dateInterval);
}
