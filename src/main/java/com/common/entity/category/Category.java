package com.common.entity.category;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;


/**
 * <h1>Base Class Category</h1>
 * 
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="CATEGORY")
public class Category extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private String name;
	private boolean isActive;
	private GroupCategory group;

	
	public static Category create(String name, GroupCategory group) {
		try {
			if(name == null)
				throw new IllegalArgumentException("category name is required");
			if(group == null)
				throw new IllegalArgumentException("group category is required");
			
			
			Category category = new Category();
			category.name = name.toLowerCase();
			category.group = group;
			category.isActive = true;
			return category;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tname: " + name 
													  + "\n\tisActive: " + isActive 
													  + "\n\tgroup: " + group.getId() + "\n}";
	}





	@OneToOne
	@JoinColumn(name="GROUP_ID")
	public GroupCategory getGroup() {return group;	}
	public void setGroup(GroupCategory group) {	this.group = group;}


	@Column(name="NAME", unique=true, updatable=false, nullable=false)
	public String getName() {	return name;}
	public void setName(String name) {	this.name = name;}
	
	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {	return isActive;}
	public void setActive(boolean isActive) {this.isActive = isActive;}
	
	
	
	
	
}
