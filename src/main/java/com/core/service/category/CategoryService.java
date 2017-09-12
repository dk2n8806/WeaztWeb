package com.core.service.category;

import java.util.List;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.core.service.GenericService;
import com.core.service.category.impl.CategoryServiceImpl;

public interface CategoryService extends GenericService<Category, Long>
{
	/** {@link CategoryServiceImpl#save} */
	Category save(String name, GroupCategory group);
	
	/** {@link CategoryServiceImpl#getByName(String)} */
	Category getByName(String name);
	
	
	
	
	/** {@link CategoryServiceImpl#getList()} */
	List<Category> getList();
	
	/** {@link CategoryServiceImpl#getCategories(Boolean)} */
	List<Category> getCategories(Boolean isActive);
	
	/** {@link CategoryServiceImpl#getByGroup(GroupCategory, Boolean)} */
	List<Category> getByGroup(GroupCategory group, Boolean isActive);
	
	/** {@link CategoryServiceImpl#setActive(Long, boolean)} */
	void setActive(Long categoryId, boolean isActive);
	
	/** {@link CategoryServiceImpl#toggleStatus(Long)} */
	void toggleStatus(Long categoryId);

	/** {@link CategoryServiceImpl#setGroup(Category, GroupCategory)} */
	void setGroup(Category category, GroupCategory group);

}
