package com.core.dao.product.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.product.ProductDao;

@Repository
public class ProductDaoImpl 
	extends GenericJpaRepository<Product, Long>
	implements ProductDao
{

	
	/** :::
	 * <p>Update product entity status</p>
	 * 
	 * @query
	 * [
	 * 		Update {@link Product} P
	 * 			Set P.status = :status
	 * 		Where P = :product
	 * ]
	 * 
	 * {@link ProductDao#updateStatus(Product, ProductStatus)}
	 * ::: */
	@Override
	public void updateStatus(Product product, ProductStatus status) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Product> query = builder.createCriteriaUpdate(Product.class);
		Root<Product> root = query.from(Product.class);
		
		query.set(root.get(Product_.status), status)
						.where(builder.equal(root, product));
		
		entityManager.createQuery(query).executeUpdate();
	}

	
	
	
	

	/** :::
	 * <p>Count total product entity </p>
	 * 
	 * @query
	 * [
	 * 		Select count(P) From {@link Product} P
	 * 		Where P.merchant = :merchant
	 * 			And P.category = :category
	 * 			And P.status = :status
	 * 			And P.createdOn Between :form And :to
	 * ]
	 * 
	 * {@link ProductDao#countProducts(Merchant, Category, ProductStatus, DateInterval)}
	 * ::: */
	@Override
	public long countProducts(Merchant merchant, Category category
										, ProductStatus status, DateInterval dateInterval) 
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		
		List<Predicate> predicates = new LinkedList<Predicate>();
		if(merchant != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.merchant), merchant)));
		}
		if(category != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.category), category)));
		}
		if(status != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.status), status)));	
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Product_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[] {}));
				
		return entityManager.createQuery(query).getSingleResult();
	}






	@Override
	public List<Product> getProducts(Merchant merchant, Category category, ProductStatus status,
			DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		
		List<Predicate> predicates = new LinkedList<Predicate>();
		if(merchant != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.merchant), merchant)));
		}
		if(category != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.category), category)));
		}
		if(status != null) {
			predicates.add(builder.and(builder.equal(root.get(Product_.status), status)));	
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Product_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[] {}))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}





	/** :::
	 * 
	 * {@link ProductDao#getByMerchant(Long, Merchant)}
	 */
	@Override
	public Product getByMerchant(Long productId, Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root)
			.where(builder.equal(root.get(Product_.id), productId)
						, builder.equal(root.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}






	/** :::
	 * <p>Retrieve a merchant by given product</p>
	 * 
	 * {@link ProductDao#getMerchant(Product)}
	 * ::: */
	@Override
	public Merchant getMerchant(Product product) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> query = builder.createQuery(Merchant.class);
		Root<Product> root = query.from(Product.class);
		query.select(root.get(Product_.merchant))
			.where(builder.equal(root, product));
		return entityManager.createQuery(query).getSingleResult();
	}

}
