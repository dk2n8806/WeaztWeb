package com.core.dao.category.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.category.Category;
import com.common.entity.category.Category_;
import com.common.entity.category.GroupCategory;
import com.core.dao.category.CategoryDao;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class CategoryDaoImpl
	extends GenericJpaRepository<Category	, Long>
	implements CategoryDao
{

//	@Override
//	public List<Category> getByGroup(GroupCategory groupCategory, Boolean isActive) {w

	
	/** ::
	 * <p>Lookup an category entity by name</p>
	 * 
	 * @query
	 * [
	 * 		Select * from {@link Category}
	 * 		Where name = :name
	 * ]
	 * 
	 * ::: */
	@Override
	public Category getByName(String name) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		
		query.select(root).where(builder.equal(root.get(Category_.name), name));
		return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<Category> getCategories(GroupCategory group, Boolean isActive) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(group != null)
			predicates.add(builder.equal(root.get(Category_.group), group));
		if(isActive != null)
			predicates.add(builder.equal(root.get(Category_.active), isActive));
		
		
		query.select(root)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.asc(root.get(Category_.name)));
		
		return getResultList(query, null);
	}


}
