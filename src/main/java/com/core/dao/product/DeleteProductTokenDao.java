package com.core.dao.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeleteProductToken;
import com.core.dao.generic.GenericRepository;
import com.core.dao.product.impl.DeleteProductTokenDaoImpl;

public interface DeleteProductTokenDao 
extends GenericRepository<DeleteProductToken, Long> {

	
	/** {@link DeleteProductTokenDaoImpl#getByMerchant(Merchant, String)} */
	DeleteProductToken getByMerchant(Merchant merchant, String token);
	
	/** {@link DeleteProductTokenDaoImpl#getByMerchant(Merchant, Boolean, Pageable)} */
	List<DeleteProductToken> getByMerchant(
			Merchant merchant, Boolean isUseable, Pageable pageable);
	
	/** {@link DeleteProductTokenDaoImpl#countByMerchant(Merchant, Boolean)} */
	long countByMerchant(Merchant merchant, Boolean isUseable);
}
