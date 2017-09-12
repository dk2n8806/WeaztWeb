package com.common.dto.review;

import com.common.entity.review.Review;

public class ReviewProductDTO extends ReviewDTO {

	private Long productId;
	private String productTitle;
	private String productImage;
	private String category;
	
	
	public ReviewProductDTO(
			Review review
			, Long reviewerId, String reviewerName, String reviewerImage
			, Long productId, String productTitle, String productImage, String category) {
		super(review, reviewerId, reviewerName, reviewerImage);
		this.productId = productId;
		this.productTitle = productTitle;
		this.productImage = productImage;
		this.category = category;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	
	
	
	

}
