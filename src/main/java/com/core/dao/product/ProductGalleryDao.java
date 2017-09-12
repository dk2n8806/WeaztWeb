package com.core.dao.product;

import java.util.List;

import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.core.dao.generic.GenericRepository;
import com.core.dao.product.impl.ProductGalleryDaoImpl;

public interface ProductGalleryDao
	extends GenericRepository<ProductGallery, Long>
{

	
	
	/** :::
	 * {@link ProductGalleryDaoImpl#getGallery(Product, Boolean)}
	 * ::: */
	List<ProductGallery> getGallery(Product product, Boolean isActive);
	
}
