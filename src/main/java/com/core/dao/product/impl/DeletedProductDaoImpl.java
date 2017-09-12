package com.core.dao.product.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeletedProduct;
import com.common.entity.product.DeletedProduct_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.product.DeletedProductDao;

@Repository
public class DeletedProductDaoImpl 
extends GenericJpaRepository<DeletedProduct, Long>
implements DeletedProductDao {

	
/**
 * Count total deleted_product by merchant
 * 
 * @query
 * [	
 * 		Select count(*)			
 * 		From {@link DeletedProduct} DP
 * 			Join DP.product P
 * 		Where P.merchant = :merchant
 * ]
 * 
 * {@link DeletedProductDao#countByMerchant(Merchant)}
 */
	@Override
	public long countByMerchant(Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<DeletedProduct> root = query.from(DeletedProduct.class);
		Join<DeletedProduct, Product> joinProduct = root.join(DeletedProduct_.product);
		
		query.select(builder.count(root))
			.where(builder.equal(joinProduct.get(Product_.merchant), merchant));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
