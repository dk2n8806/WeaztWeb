package com.common.entity.category.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;

@Entity
@DiscriminatorValue("beauty")
public class Beauty extends GroupCategory{

	private static final long serialVersionUID = 1L;

	private String name = "beauty";
	
	
	@Override	@Transient
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		return categories;
	}

	@Override	@Transient
	public String getName() {
		return this.name;
	}

}
