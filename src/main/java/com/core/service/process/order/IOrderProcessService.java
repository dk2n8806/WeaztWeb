package com.core.service.process.order;

import com.common.entity.order.OrderBundle;
import com.common.entity.product.Product;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.util.date.DateInterval;

/** 
 * <p>A service to process order</p>
 * @author dk2n_
 *
 */
public interface IOrderProcessService {

	/** {@link OrderProcessServiceImpl#generateOrder(Product, DateInterval)} */
	OrderBundle generateOrder(Product product, DateInterval dateInterval)
								throws ProductException, OrderException, MerchantException;
}
