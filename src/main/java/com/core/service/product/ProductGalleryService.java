package com.core.service.product;

import java.util.List;

import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.support.embeded.ImagePath;
import com.core.service.GenericService;
import com.core.service.product.impl.ProductGalleryServiceImpl;

public interface ProductGalleryService 
	extends GenericService<ProductGallery, Long>
{
	
	/** :::
	 * Create & save a new product_gallery entity
	 * {@link ProductGalleryServiceImpl#save(ImagePath, Product)} 
	 * ::: */
	ProductGallery save(ImagePath imagePath, Product product);
	
	/** :::
	 * Retrieve a list of product_gallery
	 * {@link ProductGalleryServiceImpl#getByProduct(Product, Boolean)}
	 * ::: */
	List<ProductGallery> getByProduct(Product product, Boolean isActive);
	
	
	/** :::
	 * Deactive a photo in the product gallery
	 * {@link ProductGalleryServiceImpl#deactive(Long, Product)}
	 * ::: */
	void deactive(Long galleryId, Product product);
}
