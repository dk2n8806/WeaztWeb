package com.common.entity.category.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;

@Entity
@DiscriminatorValue("health")
public class Health extends GroupCategory{

	private static final long serialVersionUID = 1L;



	

	@Override
	@Transient
	public String getName() {
		return "health";
	}

	@Override
	@Transient
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
//		categories.add(Category.create("skin care", this));
//		categories.add(Category.create("hair care", this));
//		categories.add(Category.create("personal care", this));
//		categories.add(Category.create("health care", this));
//		categories.add(Category.create("nail care", this));
//		categories.add(Category.create("vitamin", this));
//		categories.add(Category.create("dietary supplement", this));
//		categories.add(Category.create("sport nutrition", this));
		return categories;
	}
}
