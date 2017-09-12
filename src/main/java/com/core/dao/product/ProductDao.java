package com.core.dao.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.product.impl.ProductDaoImpl;

public interface ProductDao 
	extends GenericRepository<Product, Long>
{
	
	
	/** {@link ProductDaoImpl#getByMerchant(Long, Merchant)} */
	Product getByMerchant(Long productId, Merchant merchant);
	
	/** {@link ProductDaoImpl#getMerchant(Product)} */
	Merchant getMerchant(Product product);
	
	/** {@link ProductDaoImpl#getProducts(Merchant, Category, ProductStatus, DateInterval, Pageable)} */
	List<Product> getProducts(Merchant merchant, Category category, ProductStatus status
															, DateInterval dateInterval, Pageable pageable);

	/** {@link ProductDaoImpl#updateStatus} */
	void updateStatus(Product product, ProductStatus status);
	
	
	/** {@link ProductDaoImpl#countProducts(Merchant, Category, ProductStatus, DateInterval)} */
	long countProducts(Merchant merchant, Category category, ProductStatus status, DateInterval dateInterval);

}
