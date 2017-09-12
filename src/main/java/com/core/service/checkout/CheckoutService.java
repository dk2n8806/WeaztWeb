package com.core.service.checkout;

import com.common.entity.account.Account;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.promo.Promotion;
import com.common.exception.OrderException;
import com.common.exception.SubscriptionException;


/**
 * 
 * @author dk2n_
 *
 */
public interface CheckoutService {

	/** {@link CheckoutServiceImpl#checkout(Account, Product, int, int, Promotion)}  */
	OrderIntent checkout(Account account, Product product
			, int frequency, int numberOfShipment, Promotion promotion)
				throws SubscriptionException, OrderException;
}
