package com.core.service.product.wrapper;

import java.util.List;

import com.common.entity.product.Product;
import com.common.wrapper.ProductSubscriptionWrapper;

public interface ProductWrapperService {

	List<ProductSubscriptionWrapper> getByProduct(Product product);
}
