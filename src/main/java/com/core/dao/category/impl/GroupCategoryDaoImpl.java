package com.core.dao.category.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.category.GroupCategory;
import com.common.entity.category.group.Beverage;
import com.common.entity.category.group.Food;
import com.common.entity.category.group.Health;
import com.common.entity.category.group.Household;
import com.core.dao.category.GroupCategoryDao;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class GroupCategoryDaoImpl 
extends GenericJpaRepository<GroupCategory, Long> 
	implements GroupCategoryDao
{

	
	/** */
	@Override
	public GroupCategory getByDiscriminator(String type) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupCategory> query = builder.createQuery(GroupCategory.class);
		Root<GroupCategory> root = query.from(GroupCategory.class);
		
		Predicate predicate = null;
		
		switch (type) {
		case "beverage": 		predicate = builder.equal(root.type(), Beverage.class); 	break;
		case "household": 	predicate = builder.equal(root.type(), Household.class); 	break;
		case "health": 			predicate = builder.equal(root.type(), Health.class); 	break;
		case "food": 			predicate = builder.equal(root.type(), Food.class); 	break;
		default:
			break;
		}
		query.select(root)	.where(predicate);
		
		return entityManager.createQuery(query).getSingleResult();
	}
	

}
