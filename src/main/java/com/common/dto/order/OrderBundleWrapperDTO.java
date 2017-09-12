package com.common.dto.order;

import java.io.Serializable;

import com.common.entity.order.OrderBundle;

public class OrderBundleWrapperDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private OrderBundle orderBundle;
	private String title;
	private String image;
	private String category;
	
	
	public OrderBundleWrapperDTO(OrderBundle orderBundle, String title, String image, String category) {
		super();
		this.orderBundle = orderBundle;
		this.title = title;
		this.image = image;
		this.category = category;
	}


	public OrderBundle getOrderBundle() {
		return orderBundle;
	}


	public void setOrderBundle(OrderBundle orderBundle) {
		this.orderBundle = orderBundle;
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
	
	
	
}
