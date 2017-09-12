package com.core.service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeleteProductToken;
import com.common.entity.product.Product;
import com.core.service.GenericService;
import com.core.service.product.impl.DeleteProductTokenServiceImpl;

public interface DeleteProductTokenService extends GenericService<DeleteProductToken, Long>{

	/** {@link DeleteProductTokenServiceImpl#save(Product)} */
	DeleteProductToken save(Product product);
	
	/** {@link DeleteProductTokenServiceImpl#getByMerchant(Merchant, String)} */
	DeleteProductToken getByMerchant(Merchant merchant, String token);
	
	
	
	/** {@link DeleteProductTokenServiceImpl#markAsUsed(List)} */
	void markAsUsed(List<DeleteProductToken> tokens);
	
	/** {@link DeleteProductTokenServiceImpl#markAsUsed(DeleteProductToken)} */
	void markAsUsed(DeleteProductToken tokens);
	
	
	/** {@link DeleteProductTokenServiceImpl#getByMerchant(Merchant, Boolean, Pageable)} */
	List<DeleteProductToken> getByMerchant(
			Merchant merchant, Boolean isUseable, Pageable pageable);
	
	/** {@link DeleteProductTokenServiceImpl#countByMerchant(Merchant, Boolean)} */
	long countByMerchant(Merchant merchant, Boolean isUseable);
}
