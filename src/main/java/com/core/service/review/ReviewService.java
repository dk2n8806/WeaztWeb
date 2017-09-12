package com.core.service.review;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.review.Review;
import com.common.type.review.ReviewType;
import com.core.service.GenericService;


/**
 * 
 * @author dk
 *
 */
public interface ReviewService extends GenericService<Review, Long>
{

	/** Create & Save a new review entity
	 * {@link ReviewServiceImpl#save(Account, Product, String, ReviewType)} */
	Review save(Account account, Product product, String content, ReviewType type);
	
	
	/** Retrieve a list of reviews by an account
	 * {@link ReviewServiceImpl#getByAccount(Account, Product, ReviewType, Pageable)} */
	List<Review> getByAccount(Account account, Product product, ReviewType type, Pageable pageable);
	
	
	/** Count total reviews by an account
	 * {@link ReviewServiceImpl#countByAccount(Account, Product, ReviewType)} */
	long countByAccount(Account account, Product product, ReviewType type);
	
	
	/** Retrieve a list of reviews on a product
	 * {@link ReviewServiceImpl#getByProduct(Product, ReviewType, Pageable)} */
	List<Review> getByProduct(Product product, ReviewType type, Pageable pageable);
	
	
	/** Count total reviews on a product
	 * {@link ReviewServiceImpl#countByProduct(Product, ReviewType)} */
	long countByProduct(Product product, ReviewType type);
}
