package com.core.service.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.review.Review;
import com.common.type.review.ReviewType;
import com.core.dao.review.ReviewDao;


@Service
@Transactional
public class ReviewServiceImpl implements ReviewService
{

	@Autowired private ReviewDao reviewDao;

	@Override
	public Review findById(Long id) {
		return reviewDao.findById(id);
	}

	
	@Override
	public long getRowCount() {
		return reviewDao.getRowCount();
	}

	
	
	
	@Override
	public Review getReference(Long id) {
		return reviewDao.getReference(id);
	}

	
	
	
/**
 * <h2>Review save()</h2>
 * <p>Create & save a new review entity</p>
 * 
 * @param account 
 * @param product
 * @param content
 * @param type
 * @return new review entity or null
 * 
 * {@link ReviewService#save(Account, Product, String, ReviewType)}
 */
	@Override
	public Review save(Account account, Product product, String content, ReviewType type) {
		Review review = Review.createInstance(account, product, content, type);
		if(review != null) {
			reviewDao.persist(review);
		}
		return review;
	}

	
	
/**
 * <h1>List<Review> getByAccount()</h1>
 * <p>Retrieve a list of reviews by an account</p>
 * 
 * @param account 		(required)
 * @param product
 * @param content
 * @param type
 * @return list of reviews
 * 
 * {@link ReviewService#getByAccount(Account, Product, ReviewType, Pageable)}
 */
	@Override
	public List<Review> getByAccount(
			Account account, Product product, ReviewType type, Pageable pageable) {
		if(account == null) {
			return new ArrayList<>();
		}
		return reviewDao.getReviews(account, product, type, pageable);
	}

	
	

	
/**
 * <h1>long countByAccount()</h1>
 * <p>Count total reviews by an account</p>
 * 
 * @param account		(required)
 * @param product		
 * @param content
 * @param type
 * @return total reviews or 0
 * 
 * {@link ReviewService#countByAccount(Account, Product, ReviewType)}
 */
	@Override
	public long countByAccount(Account account, Product product, ReviewType type) {
		if(account == null) {
			return 0;
		}
		try {
			return reviewDao.countReviews(account, product, type);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	

	
/**
 * <h1>List<Review> getByProduct()</h1>
 * <p>Retrieve a list of reviews by a product</p>
 * 
 * @param product			(required)
 * @param content
 * @param type
 * @return list of reviews
 * 
 * {@link ReviewService#getByProduct(Product, ReviewType, Pageable)}
 */
	@Override
	public List<Review> getByProduct(Product product, ReviewType type,
			Pageable pageable) {
		if(product == null) {
			return new ArrayList<>();
		}
		return reviewDao.getReviews(null, product, type, pageable);
	}

	
	

	
/**
 * <h1>long countByProduct()</h1>
 * <p>Count total reviews by product</p>
 * 
 * @param product		(required)
 * @param content
 * @param type
 * @return total reviews or 0
 * 
 * {@link ReviewService#countByProduct(Product, ReviewType)}
 */
	@Override
	public long countByProduct(Product product, ReviewType type) {
		if(product == null)
		return 0;
		try {
			return reviewDao.countReviews(null, product, type);
		} catch(NoResultException e) {
			return 0;
		}
	}

}
