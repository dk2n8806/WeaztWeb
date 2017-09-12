package com.core.dao.product.wrapper;

import java.util.List;

import com.common.entity.product.Product;
import com.common.wrapper.ProductSubscriptionWrapper;
import com.core.dao.generic.GenericRepository;

public interface ProductSubscriptionWrapperDao extends GenericRepository<ProductSubscriptionWrapper, Long> {

	List<ProductSubscriptionWrapper> getSubscription(Product product);
}
