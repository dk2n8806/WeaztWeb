package com.core.dao.review.dto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.review.ReviewDTO;
import com.common.dto.review.ReviewProductDTO;
import com.common.entity.account.Account;
import com.common.entity.account.Account_;
import com.common.entity.account.Avatar_;
import com.common.entity.category.Category_;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.review.Review;
import com.common.entity.review.Review_;
import com.common.entity.support.embeded.ImagePath_;
import com.core.dao.generic.GenericJpaRepository;


/**
 * 
 * @author dk
 *
 */
@Repository
public class ReviewDTODaoImpl 
	extends GenericJpaRepository<Review, Long>
	implements ReviewDTODao 
{

	
	
/**
 * <h1>List<ReviewProductDto> getByMerchant()</h1>
 * 
 * Retrieve a list of reviews on merchant
 * 
 * @query
 * [
 * 		Select new {@link ReviewProductDTO}
 * 		From {@link Review} R
 * 			Join R.product = :product P
 * 			Join R.account A
 * 		Where P.merchant = :merchant
 * 		Order By R Desc
 * ]	
 * 
 * {@link ReviewDTODao#getByMerchant(Merchant, Pageable)}
 */
	@Override
	public List<ReviewProductDTO> 
	getByMerchant(Merchant merchant, Pageable page) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReviewProductDTO> query = builder.createQuery(ReviewProductDTO.class);
		Root<Review> root = query.from(Review.class);
		Join<Review, Account> joinAccount = root.join(Review_.account);
		Join<Review, Product> joinProduct = root.join(Review_.product);
		
		query.select(
				builder.construct(
							ReviewProductDTO.class
							, root
							, joinAccount.get(Account_.id)
							, joinAccount.get(Account_.username)
							, joinAccount.get(Account_.avatar).get(Avatar_.imagePath).get(ImagePath_.path)
							, joinProduct.get(Product_.id)
							, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
							, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
							, joinProduct.get(Product_.category).get(Category_.name)
						)
				)
			.where(
				builder.equal(joinProduct.get(Product_.merchant), merchant))
			.orderBy(builder.desc(root));
		
		return entityManager
				.createQuery(query)
				.setFirstResult(page.getOffset())
				.setMaxResults(page.getPageSize())
				.getResultList();
	}

	
	
	
	

/**
 * 
 * <h1>List<ReviewDto> getByProduct()</h1>
 * 
 * Retrieve a list of reviews on product
 * 
 * @query
 * [
 * 		Select new {@link ReviewProductDTO}
 * 		From {@link Review} R
 * 			Join R.product = :product P
 * 			Join R.account A
 * 		Where P.merchant = :merchant
 * 		Order By R Desc
 * ]	
 * 
 * {@link ReviewDTODao#getByProduct(Product, Pageable)}
 */
	public List<ReviewDTO> 
	getByProduct(Product product, Pageable page) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReviewDTO> query = builder.createQuery(ReviewDTO.class);
		Root<Review> root = query.from(Review.class);
		Join<Review, Account> joinAccount = root.join(Review_.account);
		
		query.select(
				builder.construct(
							ReviewDTO.class
							, root
							, joinAccount.get(Account_.id)
							, joinAccount.get(Account_.username)
							, joinAccount.get(Account_.avatar).get(Avatar_.imagePath).get(ImagePath_.path)
						)
				)
			.where(
				builder.equal(root.get(Review_.product), product))
			.orderBy(builder.desc(root));
		
		return entityManager
				.createQuery(query)
				.setFirstResult(page.getOffset())
				.setMaxResults(page.getPageSize())
				.getResultList();
	}

}
