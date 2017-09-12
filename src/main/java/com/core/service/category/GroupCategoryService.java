package com.core.service.category;

import java.util.List;

import com.common.entity.category.GroupCategory;
import com.core.service.GenericService;
import com.core.service.category.impl.GroupCategoryServiceImpl;

public interface GroupCategoryService extends GenericService<GroupCategory, Long> {

	/** {@link GroupCategoryServiceImpl#save(GroupCategory)} */
	GroupCategory save(GroupCategory category);
	
	List<GroupCategory> getList();
	
	
	
	/** {@link GroupCategoryServiceImpl#getByName} */
	GroupCategory getByName(String type);
	
	
}
