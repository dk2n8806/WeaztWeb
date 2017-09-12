package com.core.service.category.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.core.dao.category.CategoryDao;
import com.core.service.category.CategoryService;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService
{

	@Autowired private CategoryDao categoryDao;
	
	
	/** :::
	 * Create & save a new category entity
	 * ::: */ 
	@Override
	public Category save(String name, GroupCategory group) {
		Category category = Category.create(name, group);
		if(category != null) {
			categoryDao.persist(category);
		}
		return category;
	}

	
	
	
	/*** :::
	 * @return category entity by a given category_id
	 * ::: */
	@Override
	public Category findById(Long id) {
		return categoryDao.findById(id);
	}

	
	
	/** :::
	 * @return category proxy by a given category_id
	 * :::: */
	@Override
	public Category getReference(Long id) {
		return categoryDao.getReference(id);
	}

	
	
	/** :::
	 * @return total category entities
	 */
	@Override
	public long getRowCount() {
		return categoryDao.getRowCount();
	}




	@Override
	public List<Category> getList() {
		return categoryDao.getList();
	}







	/** :::
	 * 
	 */
	@Override
	public Category getByName(String name) {
		try {
			return categoryDao.getByName(name);
		} catch(NoResultException e){
			return null;
		}
	}




	/** :::
	 * <p>Update category status</p>
	 */
	@Override
	public void setActive(Long categoryId, boolean isActive) {
		Category category = categoryDao.findById(categoryId);
		if(category != null) {
			category.setActive(isActive);
			categoryDao.update(category);
		}
	}




	@Override
	public void toggleStatus(Long categoryId) {
		Category category = categoryDao.findById(categoryId);
		if(category != null) {
			category.setActive(!category.isActive());
			categoryDao.update(category);
		}
	}




	@Override
	public List<Category> getByGroup(GroupCategory group, Boolean isActive) {
		if(group == null) return new ArrayList<>();
		return categoryDao.getCategories(group, isActive);
	}




	@Override
	public List<Category> getCategories(Boolean isActive) {
		return categoryDao.getCategories(null, isActive);
	}




	@Override
	public void setGroup(Category category, GroupCategory group) {
		category.setGroup(group);
		categoryDao.update(category);
	}



}
