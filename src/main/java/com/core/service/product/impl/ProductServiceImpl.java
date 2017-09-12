package com.core.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.support.embeded.ImagePath;
import com.common.exception.ProductException;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.product.ProductDao;
import com.core.service.product.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{

	
	@Autowired private ProductDao productDao;
	
	
	
	/** :::
	 * <p>Create & save a new product entity<p>
	 * {@link ProductService#save}
	 */
	@Override
	public Product save(Merchant merchant, Category category
										, BasicInfo basicInfo, SubscriptionInfo subscriptionInfo
										, ShippingInfo shippingInfo
										, ImagePath displayImage, List<ProductGallery> gallery
																	) 
	{
		Product product = Product.create(merchant, category
																		, basicInfo, subscriptionInfo, shippingInfo
																		, displayImage, gallery);
		if(product != null) {
			productDao.persist(product);
		}
		return product;
	}



	
	/** :::
	 * <p>Make the product visible to public</p>
	 * {@link ProductService#makePublic(Product)}
	 * ::: */
	@Override
	public void makePublic(Product product) throws ProductException {
		if(product.getStatus().equals(ProductStatus.DELETED)) 
			throw new ProductException("Invalid product state");
	
		productDao.updateStatus(product, ProductStatus.PUBLIC);
		product.setStatus(ProductStatus.PUBLIC);
	}



	/** :::
	 * <p>Make the product private</p>
	 * {@link ProductService#makePrivate(Product)}
	 * ::: */
	@Override
	public void makePrivate(Product product)  throws ProductException{
		if(product.getStatus().equals(ProductStatus.DELETED)) 
			throw new ProductException("Invalid product state");
	
		productDao.updateStatus(product, ProductStatus.PRIVATE);
		product.setStatus(ProductStatus.PRIVATE);
	}



	/** ::::
	 * <p>Mark the product as "DELETED"</p>
	 * {@link ProductService#makeDelete(Product)}
	 * ::: */
	@Override
	public void makeDelete(Product product) {
		productDao.updateStatus(product, ProductStatus.DELETED);
		product.setStatus(ProductStatus.DELETED);
	}



	
	/** :::
	 * <p>Count total product</p>
	 * {@link ProductService#countProducts(Category, ProductStatus, DateInterval)}
	 * ::: */
	@Override
	public long countProducts(Category category, ProductStatus status, DateInterval dateInterval) 
	{
		try {
			return productDao.countProducts(null, category, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}



	/** ::::
	 * <p>Count total product by the merchant</p>
	 * {@link ProductService#countByMerchant(Merchant, Category, ProductStatus, DateInterval)}
	 * ::: */
	@Override
	public long countByMerchant(Merchant merchant, Category category
													, ProductStatus status, DateInterval dateInterval) 
	{
		if(merchant == null) {
			return 0;
		}
		try {
			return productDao.countProducts(merchant, category, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}




	/** :::
	 * <p>Lookup product entity by a given product_id<p>
	 * {@link ProductService#findById(Long)}
	 * ::: */
	@Override
	public Product findById(Long id) {
		return productDao.findById(id);
	}





	/** :::
	 * <p>Retrieve a product proxy by a given product_id</p>
	 * {@link ProductService#getReference(Long)}
	 * ::: */
	@Override
	public Product getReference(Long id) {
		return productDao.getReference(id);
	}





	/** :::
	 * <p>Count total product entities</p>
	 * {@link ProductService#getRowCount()}
	 * ::: */
	@Override
	public long getRowCount() {
		return productDao.getRowCount();
	}




	@Override
	public List<Product> getByMerchant(Merchant merchant, Category category, ProductStatus status,
			DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) return new ArrayList<>();
		return productDao.getProducts(merchant, category, status, dateInterval, pageable);
	}




	@Override
	public List<Product> getProducts(Category category, ProductStatus status, DateInterval dateInterval,
			Pageable pageable) {
		Merchant merchant = null;
		return productDao.getProducts(merchant , category, status, dateInterval, pageable);
	}




	/** :::
	 * {@link ProductService#getByMerchant(Long, Merchant)}
	 */
	@Override
	public Product getByMerchant(Long productId, Merchant merchant) {
		try {
			return productDao.getByMerchant(productId, merchant);
		} catch(NoResultException e) {
			return null;
		}
	}




	/** :::
	 * <p>Update the product entity</p>
	 * {@link ProductService#update(Product)}
	 * ::: */
	@Override
	public void update(Product product) throws ProductException {
		if(product.getStatus().equals(ProductStatus.DELETED))
			throw new ProductException("Invalid product stage");
		productDao.update(product);
	}




	/** :::
	 * <p>Lookup a merchant by a given product</p>
	 * 
	 * {@link ProductService#getMerchant(Product)}
	 * ::: */
	@Override
	public Merchant getMerchant(Product product) {
		try {
			return productDao.getMerchant(product);
		} catch(NoResultException e) {
			return null;
		}
	}

}
