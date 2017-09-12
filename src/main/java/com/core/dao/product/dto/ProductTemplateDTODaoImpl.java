package com.core.dao.product.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.entity.category.Category_;
import com.common.entity.category.GroupCategory;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class ProductTemplateDTODaoImpl  
	extends GenericJpaRepository<ProductTemplateDTO, Long> 
implements ProductTemplateDTODao, SearchProductTemplateDTODao
{

	
	
	/** :::
	 * <p>Retrieve a list of templates of products</p>
	 * 
	 * @query
	 * [
	 * 		Select new {@link ProductTemplateDTO}
	 * 		From {@link Product} P
	 * 		Where P.merchant = :merchant
	 * 			And P.category = :category
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from  And :to
	 * 		Order by P DESC
	 * ]
	 * 
	 * {@link ProductTemplateDTODao#getTemplates}
	 * ::: */
	@Override
	public List<ProductTemplateDTO> getTemplates(Merchant merchant, List<Category> categories
										, ProductStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductTemplateDTO> query = builder.createQuery(ProductTemplateDTO.class);
		Root<Product> root = query.from(Product.class);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> catPredicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(root.get(Product_.merchant), merchant));
		if(status != null) 
			predicates.add(builder.equal(root.get(Product_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Product_.createdOn)
																, dateInterval.getFrom(), dateInterval.getTo()));
		
		if(categories != null) {
			for(Category  category : categories) {
				if(category != null) {
					catPredicates.add(builder.equal(root.get(Product_.category), category));
				}
			}
		}
		if(catPredicates.size() > 0) {
			predicates.add(builder.or(catPredicates.toArray(new Predicate[]{})));	
		}
		
		
		query.select(builder.construct(ProductTemplateDTO.class
														,  root.get(Product_.id)
														, root.get(Product_.basicInfo).get(BasicInfo_.title)
														, root.get(Product_.basicInfo).get(BasicInfo_.price)
														, root.get(Product_.displayImage).get(ImagePath_.path)
														, root.get(Product_.category).get(Category_.name)
													)
							)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	
	

	
	/** :::
	 * <p>Retrieve a list of templates of products</p>
	 * 
	 * @query
	 * [
	 * 		Select new {@link ProductTemplateDTO}
	 * 		From {@link Product} P
	 * 		Where P.merchant = :merchant
	 * 			And P.category.group = :category
	 * 			And P.status = :status
	 * 			And P.createdOn Between :from  And :to
	 * 		Order by P DESC
	 * ]
	 * 
	 * {@link ProductTemplateDTODao#getTemplates}
	 * ::: */
	@Override
	public List<ProductTemplateDTO> getTemplates(Merchant merchant, GroupCategory category, ProductStatus status,
			DateInterval dateInterval, Pageable pageable) {final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductTemplateDTO> query = builder.createQuery(ProductTemplateDTO.class);
			Root<Product> root = query.from(Product.class);
			
			List<Predicate> predicates = new ArrayList<>();
			if(merchant != null)
				predicates.add(builder.equal(root.get(Product_.merchant), merchant));
			if(category != null)
				predicates.add(builder.equal(root.get(Product_.category).get(Category_.group), category));
			if(status != null) 
				predicates.add(builder.equal(root.get(Product_.status), status));
			if(dateInterval != null)
				predicates.add(builder.between(root.get(Product_.createdOn)
																	, dateInterval.getFrom(), dateInterval.getTo()));
			
			query.select(builder.construct(ProductTemplateDTO.class
															,  root.get(Product_.id)
															, root.get(Product_.basicInfo).get(BasicInfo_.title)
															, root.get(Product_.basicInfo).get(BasicInfo_.price)
															, root.get(Product_.displayImage).get(ImagePath_.path)
															, root.get(Product_.category).get(Category_.name)
														)
								)
					.where(predicates.toArray(new Predicate[]{}))
					.orderBy(builder.desc(root));
			return getResultList(query, pageable);
	}

	
	
	

	/** :::
	 * <p>Retrieve a list of product template that have </p>
	 * 
	 * @query
	 * [
	 * 		
	 * ]
	 * 
	 * {@link SearchProductTemplateDTODao#getByTitle(String[], ProductStatus, Pageable)}
	 */
	@Override
	public List<ProductTemplateDTO> getByTitle(String title[], ProductStatus status, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductTemplateDTO> query = builder.createQuery(ProductTemplateDTO.class);
		Root<Product> root = query.from(Product.class);
		

		List<Predicate> predicates = new ArrayList<>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Product_.status), status));
		}
		if(title != null && title.length > 0) {
			Predicate[] searchPredicates = new Predicate[title.length];
			for(int i = 0; i < title.length; i++) {
				searchPredicates[i] = 
						builder.like(
							builder.lower(root.get(Product_.basicInfo).get(BasicInfo_.title))
							, title[i]);
			}
			predicates.add(builder.or(searchPredicates));
		}
		
		query.select(
				builder.construct(
						ProductTemplateDTO.class
						,  root.get(Product_.id)
						, root.get(Product_.basicInfo).get(BasicInfo_.title)
						, root.get(Product_.basicInfo).get(BasicInfo_.price)
						, root.get(Product_.displayImage).get(ImagePath_.path)
						, root.get(Product_.category).get(Category_.name)
					)
				)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));

		
		return entityManager.createQuery(query)
							.setFirstResult(pageable.getOffset())
							.setMaxResults(pageable.getPageSize())
							.getResultList();
	}
	
	
	/** :::
	 * <p>Count total product by title</p>
	 * 
	 * {@link SearchProductTemplateDTODao#countByTitle(String[], ProductStatus)}
	 */
	@Override
	public long countByTitle(String title[], ProductStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Product_.status), status));
		}
		if(title != null && title.length > 0) {
			Predicate[] searchPredicates = new Predicate[title.length];
			for(int i = 0; i < title.length; i++) {
				searchPredicates[i] = 
						builder.like(
							builder.lower(root.get(Product_.basicInfo).get(BasicInfo_.title))
							, title[i]);
			}
			predicates.add(builder.or(searchPredicates));
		}
		
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}





	
	/** :::
	 * <p>Retrieve a template of a product</p>
	 * 
	 * @query
	 * [
	 * 		Select new {@link ProductTemplateDTO}
	 * 		From {@link Product} P
	 * 		Where P = :product
	 * ]
	 * 
	 * {@link ProductTemplateDTODao#getTemplate(Product)}
	 * ::: */
	@Override
	public ProductTemplateDTO getTemplate(Product product) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductTemplateDTO> query = builder.createQuery(ProductTemplateDTO.class);
		Root<Product> root = query.from(Product.class);
		query.select(builder.construct(ProductTemplateDTO.class
														,  root.get(Product_.id)
														, root.get(Product_.basicInfo).get(BasicInfo_.title)
														, root.get(Product_.basicInfo).get(BasicInfo_.price)
														, root.get(Product_.displayImage).get(ImagePath_.path)
														, root.get(Product_.category).get(Category_.name)
													)
							)
				.where(builder.equal(root, product));
		return entityManager.createQuery(query).getSingleResult();
	}
	
}
