package com.common.dto.product;

import java.io.Serializable;

import com.common.entity.subscriber.Bookmark;

public class BookmarkProductTemplateDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String image;
	private String category;
	private int price;
	private Bookmark bookmark;
	
	
	
	
	public BookmarkProductTemplateDTO(String title, String image, String category, int price, Bookmark bookmark) {
		super();
		this.title = title;
		this.image = image;
		this.category = category;
		this.price = price;
		this.bookmark = bookmark;
	}
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	
	public Bookmark getBookmark() {
		return bookmark;
	}
	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
	

}
