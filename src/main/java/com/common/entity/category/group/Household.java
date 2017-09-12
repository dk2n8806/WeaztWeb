package com.common.entity.category.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;

@Entity
@DiscriminatorColumn(name="household")
public class Household extends GroupCategory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Override
	@Transient
	public String getName() {
		return "household";
	}
	
	@Override
	@Transient
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
//		categories.add(Category.create("paper & plastic products", this));
//		categories.add(Category.create("laundry", this));
//		categories.add(Category.create("cleaning products", this));
//		categories.add(Category.create("trash bags", this));
//		categories.add(Category.create("home  fragance", this));
//		categories.add(Category.create("food storage", this));
//		categories.add(Category.create("insect & pest control", this));
//		categories.add(Category.create("natural home shop", this));
		return categories;
	}
	
}
