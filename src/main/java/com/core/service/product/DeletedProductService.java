package com.core.service.product;


import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeletedProduct;
import com.common.entity.product.Product;
import com.core.service.product.impl.DeletedProductServiceImpl;

public interface DeletedProductService {

	/** {@link DeletedProductServiceImpl#save(Product)} */
	DeletedProduct save(Product product);
	
	/** {@link DeletedProductServiceImpl#countByMerchant(Merchant)} */
	long countByMerchant(Merchant merchant);

}
