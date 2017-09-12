package com.core.dao.category;

import com.common.entity.category.GroupCategory;
import com.core.dao.category.impl.GroupCategoryDaoImpl;
import com.core.dao.generic.GenericRepository;

public interface GroupCategoryDao extends GenericRepository<GroupCategory, Long>{
	
	/** {@link GroupCategoryDaoImpl#getByDiscriminator(String)} */
	GroupCategory getByDiscriminator(String type);
	
}
