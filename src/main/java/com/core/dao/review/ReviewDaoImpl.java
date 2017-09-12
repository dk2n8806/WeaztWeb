package com.core.dao.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.review.Review;
import com.common.entity.review.Review_;
import com.common.type.review.ReviewType;
import com.core.dao.generic.GenericJpaRepository;

/**
 * 
 * @author dk
 *
 */
@Repository
public class ReviewDaoImpl 
	extends GenericJpaRepository<Review, Long>
	implements ReviewDao 
{


/**
 * <h3>List<Review> getByProduct</h3>
 * 
 * Retrieve a list of
 * 
 * @query
 * [
 * 		Select R
 * 		From {@link Review} R
 * 		Where R.account = :account
 * 			And R.product = :product
 * 			And R.type = :type
 * 		Order By R Desc
 * ]
 * 
 * @param account 	(required = false)
 * @param product 	(required = false)
 * @param type 		(required = false)
 * @param pageable  
 */
	@Override
	public List<Review> getReviews(
			Account account, Product product, ReviewType type, Pageable pageable) 
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Review> query = builder.createQuery(Review.class);
		Root<Review> root = query.from(Review.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) {
			predicates.add(builder.equal(root.get(Review_.account), account));
		}
		if(product != null) {
			predicates.add(builder.equal(root.get(Review_.product), product));
		}
		if(type != null) {
			predicates.add(builder.equal(root.get(Review_.type), type));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return entityManager.createQuery(query)
						.setFirstResult(pageable.getOffset())
						.setMaxResults(pageable.getPageSize())
						.getResultList();
	}

	

/**
 * <h3>long getByProduct</h3>
 * 
 * Count total reviews
 * 
 * @query
 * [
 * 		Select count(R)
 * 		From {@link Review} R
 * 		Where R.account = :account
 * 			And R.product = :product
 * 			And R.type = :type
 * ]
 * 
 * @param account 	(required = false)
 * @param product 	(required = false)
 * @param type 		(required = false)
 */
	@Override
	public long countReviews(Account account, Product product, ReviewType type) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Review> root = query.from(Review.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) {
			predicates.add(builder.equal(root.get(Review_.account), account));
		}
		if(product != null) {
			predicates.add(builder.equal(root.get(Review_.product), product));
		}
		if(type != null) {
			predicates.add(builder.equal(root.get(Review_.type), type));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}

}
