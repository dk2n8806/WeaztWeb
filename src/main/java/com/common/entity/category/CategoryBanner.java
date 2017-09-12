package com.common.entity.category;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.common.entity.support.embeded.ImagePath;

public class CategoryBanner {

	private GroupCategory group;
	private ImagePath image;
	private boolean isPrimary;
	
	public static CategoryBanner create(GroupCategory group, ImagePath image) {
		try {
			if(group == null)
				throw new IllegalArgumentException("Invalid category_group object");
			if(image == null || !image.isActive())
				throw new IllegalArgumentException("Invalid image_path object");
			
			CategoryBanner a = new CategoryBanner();
			a.group = group;
			a.image = image;
			a.isPrimary = true;
			return a;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Type(type="yes_no")
	@Column(name="IS_PRIMARY", nullable=false)
	public boolean isPrimary() {return isPrimary;}
	public void setPrimary(boolean isPrimary) {	this.isPrimary = isPrimary;}
	
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID", nullable=false)
	public GroupCategory getGroup() {return group;}
	public void setGroup(GroupCategory group) {this.group = group;}
	
	@OneToOne
	@JoinColumn(name="IMAGE_ID", nullable=false)
	public ImagePath getImage() {	return image;}
	public void setImage(ImagePath image) {this.image = image;}
	
}
