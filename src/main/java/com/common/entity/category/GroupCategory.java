package com.common.entity.category;


import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.category.group.Beverage;


@Entity
@org.hibernate.annotations.DiscriminatorOptions(force=true)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "GROUP_NAME" )
@Table(name="CATEGORY_GROUP")
public abstract class GroupCategory extends AbstractPersistenceObject  {

	private static final long serialVersionUID = 1L;

	public static GroupCategory create(String name) {
		if(name.equals(new Beverage().getName())) {
			return new Beverage();
		}
		return null;
	}
	
	
	@Transient public abstract List<Category> getCategories();
	@Transient public abstract String getName();
	
	
	
}
