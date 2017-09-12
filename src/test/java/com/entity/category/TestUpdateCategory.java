package com.entity.category;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.core.service.category.CategoryService;
import com.core.service.category.GroupCategoryService;

public class TestUpdateCategory extends BaseTest{

	@Autowired private CategoryService categoryService;
	@Autowired private GroupCategoryService groupService;
	
	@Test
	public void test() {
		List<Category> categories = categoryService.getCategories(null);
		GroupCategory group = groupService.getByName("beverage");
		
		for(Category category : categories) {
			categoryService.setGroup(category, group);
		}
 	}
}
