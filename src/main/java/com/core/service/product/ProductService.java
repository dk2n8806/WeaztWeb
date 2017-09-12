package com.core.service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

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
import com.core.service.GenericService;
import com.core.service.product.impl.ProductServiceImpl;

public interface ProductService extends GenericService<Product, Long>{

	
	/** ::: 
	 * Create & save a new product entity
	 * {@link ProductServiceImpl#save}
	 * ::: */
	Product save(Merchant merchant, Category category
			, BasicInfo basicInfo, SubscriptionInfo subscriptionInfo, ShippingInfo shippingInfo
			, ImagePath displayImage, List<ProductGallery> gallery);
	
	
	/** {@link ProductServiceImpl#getMerchant(Product)} */
	Merchant getMerchant(Product product);

	/** ::: {@link ProductServiceImpl#update(Product)} ::: */
	void update(Product product) throws ProductException;
	
	/** :::
	 * Mark product status as "PUBLIC"
	 * {@link ProductServiceImpl#makePublic(Product)}
	 * ::: */
	void makePublic(Product product) throws ProductException;
	
	
	/** ::: 
	 * Mark product status as "PRIVATE"
	 * {@link ProductServiceImpl#makePrivate(Product)}
	 * ::: */
	void makePrivate(Product product) throws ProductException;
	
	
	/** ::: 
	 * Mark product status as "DELETED"
	 * {@link ProductServiceImpl#makeDelete(Product)}
	 * ::: */
	void makeDelete(Product product);
	
	
	/** :::
	 * @return product entity
	 * {@link ProductServiceImpl#getByMerchant(Long, Merchant)}
	 */
	Product getByMerchant(Long productId, Merchant merchant);
	
	
	/** {@link ProductServiceImpl#getByMerchant(Merchant, Category, ProductStatus, DateInterval, Pageable)} */
	List<Product> getByMerchant(Merchant merchant, Category category, ProductStatus status
															, DateInterval dateInterval, Pageable pageable);

	/** {@link ProductServiceImpl#getProducts(Category, ProductStatus, DateInterval, Pageable)} */
	List<Product> getProducts(Category category, ProductStatus status
															, DateInterval dateInterval, Pageable pageable);
	
	
	
	/** :::
	 * @return total products 
	 * {@link ProductServiceImpl#countProducts(Category, ProductStatus, DateInterval)}
	 * ::: */
	long countProducts(Category category, ProductStatus status, DateInterval dateInterval);
	
	
	
	

	/** :::
	 * @return total products of the merchant
	 * {@link ProductServiceImpl#countByMerchant(Merchant, Category, ProductStatus, DateInterval)}
	 * ::: */
	long countByMerchant(Merchant merchant, Category category, ProductStatus status, DateInterval dateInterval);


	
}
