package com.common.dto.product;

import java.io.Serializable;

public class ProductTemplateDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private int price;
	private String category;
	private String image;
	
	
	
	public ProductTemplateDTO(Long id, String title, int price, String image, String category) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.price = price;
		this.category = category;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tid: " + id + "\n\ttitle: " + title + "\n\tprice: " + price
				+ "\n\tcategory: " + category + "\n\timage: " + image + "\n}";
	}





	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
