package com.entity.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.category.group.Beverage;
import com.common.entity.category.group.Food;
import com.common.entity.category.group.Health;
import com.common.entity.category.group.Household;
import com.core.service.category.CategoryService;
import com.core.service.category.GroupCategoryService;

public class TestCreateCategory extends BaseTest {

	@Autowired private CategoryService categoryService;
	@Autowired private GroupCategoryService groupCategoryService;
	

//	@Test
//	public void test() {
//		GroupCategory group = groupCategoryService.getByName("beverage");
//		Category category = categoryService.save("men", group);
//		System.out.println(category.getId());
//	}
	
	@After
	public void complete() {
		List<Category> categories = categoryService.getList();
		for(Category c : categories) {
			System.out.println(c);
		}
	}
	
	@Test
	public void testCreateBeverage() {
		Beverage beverage = (Beverage)groupCategoryService.save(new Beverage());
		//Health beauty = (Health)groupCategoryService.save(new Health());
		//Food food = (Food)groupCategoryService.save(new Food());
		//Household household = (Household) groupCategoryService.save(new Household());
		
		List<Category> categories = new ArrayList<>();
		categories.addAll(beverage.getCategories());
		//categories.addAll(beauty.getCategories());
		//categories.addAll(food.getCategories());
		//categories.addAll(household.getCategories());
		
		for(Category category : categories) {
			categoryService.save(category.getName(), category.getGroup());
		}	
	}
	
	
	public static Map<GroupCategory, List<Category>> getCategories() {
		Map<GroupCategory, List<Category>> map = new HashMap<>();
		GroupCategory beverage = new Beverage();
		map.put(beverage, getBeverage(beverage));
		return map;
	}
	
	public static List<Category> getBeverage(GroupCategory beverage) {
		List<Category> map = new ArrayList<>();
		map.add(new Category());
		return map;
	}
	
	
}
