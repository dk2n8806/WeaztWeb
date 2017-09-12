package com.core.dao.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.ProductGallery_;
import com.common.entity.support.embeded.ImagePath_;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.product.ProductGalleryDao;

@Repository
public class ProductGalleryDaoImpl 
	extends GenericJpaRepository<ProductGallery, Long>
implements ProductGalleryDao
{

	
	/** :::
	 * <p>Retrieve a list of photo of the product</p>
	 * 
	 * @query
	 * [
	 * 		Select P From {@link ProductGallery} P
	 * 		Where P.product = :product
	 * 			And P.imagePath.active  = :isActive
	 * 		Order By P Desc
	 * ]
	 * 
	 * {@link ProductGalleryDao#getGallery(Product, Boolean)}
	 * ::: */
	@Override
	public List<ProductGallery> getGallery(Product product, Boolean isActive) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductGallery> query = builder.createQuery(ProductGallery.class);
		Root<ProductGallery> root =  query.from(ProductGallery.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(product != null) {
			predicates.add(builder.equal(root.get(ProductGallery_.product), product));
		}
		if(isActive != null) {
			predicates.add(builder.equal(root.get(ProductGallery_.imagePath).get(ImagePath_.active), isActive));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return entityManager.createQuery(query).getResultList();
	}

	

}
