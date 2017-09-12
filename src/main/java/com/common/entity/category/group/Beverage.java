package com.common.entity.category.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;

@Entity
@DiscriminatorValue("beverage")
public class Beverage extends GroupCategory {

	private static final long serialVersionUID = 1L;


	@Override
	@Transient
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		categories.add(Category.create("coffee", this));
		categories.add(Category.create("juices", this));
		categories.add(Category.create("protein drinks", this));
		categories.add(Category.create("sport & energy", this));
		categories.add(Category.create("tea", this));
		
		
		
		
		
		//categories.add(Category.create("milk & cream", this));
		//categories.add(Category.create("coctail mixers", this));
		//categories.add(Category.create("drink mixes & syrups", this));
		//categories.add(Category.create("water", this));
		//categories.add(Category.create("soft drinks", this));
		return categories;
	}


	@Override
	@Transient
	public String getName() {
		return "beverage";
	}
}
