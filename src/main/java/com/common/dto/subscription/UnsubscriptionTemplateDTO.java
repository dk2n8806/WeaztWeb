package com.common.dto.subscription;

import java.io.Serializable;

import com.common.entity.subscription.Unsubscription;

public class UnsubscriptionTemplateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private String productTitle;
	private String productImage;
	private String category;
	private Unsubscription unsubscription;
	
	public UnsubscriptionTemplateDTO(Long productId
								, String productTitle
								, String productImage
								, String category
								, Unsubscription unsubscription) {
		this.productId = productId;
		this.productTitle = productTitle;
		this.productImage = productImage;
		this.category = category;
		this.unsubscription = unsubscription;
	}

	

	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public String getProductTitle() {
		return productTitle;
	}



	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}



	public String getProductImage() {
		return productImage;
	}



	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Unsubscription getUnsubscription() {
		return unsubscription;
	}
	public void setUnsubscription(Unsubscription unsubscription) {
		this.unsubscription = unsubscription;
	}
	
	
}
