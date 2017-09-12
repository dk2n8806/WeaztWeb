package com.core.service.product.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.support.embeded.ImagePath;
import com.core.dao.product.ProductGalleryDao;
import com.core.service.product.ProductGalleryService;

/**
 * @author dk2n_
 *
 */
@Service
@Transactional
public class ProductGalleryServiceImpl implements ProductGalleryService{

	@Autowired private ProductGalleryDao galleryDao;
	
	
	/** :::
	 * 
	 * ::: */
	@Override
	public ProductGallery findById(Long id) {
		return galleryDao.findById(id);
	}

	

	/** :::
	 * 
	 * ::: */
	@Override
	public ProductGallery getReference(Long id) {
		return galleryDao.getReference(id);
	}

	

	/** :::
	 * 
	 * ::: */
	@Override
	public long getRowCount() {
		return galleryDao.getRowCount();
	}

	

	/** :::
	 * <p>Retrieve a list of gallery of the product</p>
	 * {@link ProductGalleryService#getByProduct(Product, Boolean)}
	 * ::: */
	@Override
	public List<ProductGallery> getByProduct(Product product, Boolean isActive) 
	{
		if(product == null) {
			return new ArrayList<>();
		}
		return galleryDao.getGallery(product, isActive);
	}



	/** :::
	 * <p></p>
	 * ::: */
	@SuppressWarnings("deprecation")
	@Override
	public void deactive(Long galleryId, Product product) {
		ProductGallery photo = findById(galleryId);
		if(photo != null && photo.getProduct().getId().equals(product.getId())) {
			ImagePath imagePath = photo.getImagePath();
			imagePath.setActive(false);
			galleryDao.update(photo);
		}
	}



	/** :::
	 * <p>Create & save product_gallery entity</p>
	 * {@link ProductGalleryService#save(ImagePath, Product)}
	 * ::: */
	@Override
	public ProductGallery save(ImagePath imagePath, Product product) {
		ProductGallery gallery = ProductGallery.createEntityInstance(product, imagePath);
		if(gallery != null) {
			galleryDao.persist(gallery);
		}
		return gallery;
	}

}
