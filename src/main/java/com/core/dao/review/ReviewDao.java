package com.core.dao.review;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.review.Review;
import com.common.type.review.ReviewType;
import com.core.dao.generic.GenericRepository;

/**
 * 
 * @author dk
 *
 */
public interface ReviewDao extends GenericRepository<Review, Long>
{

	/** Retrieve a list of review entities by product
	 * {@link ReviewDaoImpl#getReviews(Account, Product, ReviewType, Pageable)} */
	List<Review> getReviews(Account account, Product product, ReviewType type, Pageable pageable);
	
	
	/** Count total reviews on the product
	 * {@link ReviewDaoImpl#countReviews(Account, Product, ReviewType)} */
	long countReviews(Account account, Product product, ReviewType type);
}
