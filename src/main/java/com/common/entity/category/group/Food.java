package com.common.entity.category.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;

@Entity
@DiscriminatorValue("food")
public class Food extends GroupCategory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	@Transient
	public String getName() {
		return "food";
	}
	
	
	@Transient
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
//		categories.add(Category.create("breakfast", this));
//		categories.add(Category.create("snacks", this));
//		categories.add(Category.create("canned & jarred food", this));
//		categories.add(Category.create("cooking & backing supplies", this));
		return categories;
	}
	
}
