package com.core.service.shippo;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.core.service.shippo.impl.ShippoQuoteServiceImpl;

/**
 * <p>A service to get a quote on shipping</p>
 * 
 * @author dk2n_
 *
 */
public interface ShippoQuoteService {

	/** {@link ShippoQuoteServiceImpl#getShippingCost(Account, Product)} */
	Integer getShippingCost(Account customer, Product product);
}
