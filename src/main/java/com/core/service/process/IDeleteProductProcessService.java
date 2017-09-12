package com.core.service.process;

import com.common.entity.product.Product;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;

/**
 * 
 * @author dk2n_
 *
 */
public interface IDeleteProductProcessService {

	/** {@link DeleteProductProcess#delete(Product)} */
	void delete(Product product) throws ProductException, SubscriptionException, OrderException;
}
