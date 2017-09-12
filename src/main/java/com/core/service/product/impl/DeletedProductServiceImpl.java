package com.core.service.product.impl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeletedProduct;
import com.common.entity.product.Product;
import com.core.dao.product.DeletedProductDao;
import com.core.service.product.DeletedProductService;

@Service
@Transactional
public class DeletedProductServiceImpl implements DeletedProductService{

	@Autowired private DeletedProductDao deletedDao;

	
		
	/**
	 * Create & persist new deleted_product
	 * {@link DeletedProductService#save(Product)}
	 */
	@Override
	public DeletedProduct save(Product product) {
		DeletedProduct deletedProduct = DeletedProduct.create(product);
		if(deletedProduct != null)
			deletedDao.persist(deletedProduct);
		return deletedProduct;
	}

	
	
		
	/**
	 * Count total deleted_product by merchant
	 * {@link DeletedProductService#countByMerchant(Merchant)}
	 */
	@Override
	public long countByMerchant(Merchant merchant) {
		if(merchant == null){
			return 0;
		}
		try {
			return deletedDao.countByMerchant(merchant);
		} catch(NoResultException e) {
			return 0;
		}
	}

}
