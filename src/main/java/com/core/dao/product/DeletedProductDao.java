package com.core.dao.product;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeletedProduct;
import com.core.dao.generic.GenericRepository;
import com.core.dao.product.impl.DeletedProductDaoImpl;

public interface DeletedProductDao extends GenericRepository<DeletedProduct, Long>{

	/** {@link DeletedProductDaoImpl#countByMerchant(Merchant)} */
	long countByMerchant(Merchant merchant);
}
