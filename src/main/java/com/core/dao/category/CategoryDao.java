package com.core.dao.category;


import java.util.List;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.core.dao.category.impl.CategoryDaoImpl;
import com.core.dao.generic.GenericRepository;

public interface CategoryDao 
	extends GenericRepository<Category, Long>
{

	/** {@link CategoryDaoImpl#getByName(String)} */
	Category getByName(String name);
	
	/** {@link CategoryDaoImpl#getCategories(GroupCategory, Boolean)} */
	List<Category> getCategories(GroupCategory group, Boolean isActive);
	
}
