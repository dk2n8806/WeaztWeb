package com.common.dto.product;

import java.io.Serializable;

import com.common.entity.product.DeletedProduct;

public class DeletedProductTemplateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String image;
	private String category;
	private DeletedProduct product;
	
	
	
	
	public DeletedProductTemplateDTO(String title, String image, String category, DeletedProduct product) {
		super();
		this.title = title;
		this.image = image;
		this.category = category;
		this.product = product;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public DeletedProduct getProduct() {
		return product;
	}
	public void setProduct(DeletedProduct product) {
		this.product = product;
	}
	

	
	
	
}
