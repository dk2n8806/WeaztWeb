package com.common.dto.review;

import com.common.entity.review.Review;

public class ReviewDTO {

	private Review review;
	private Long reviewerId;
	private String reviewerName;
	private String reviewerImage;
	
	
	
	public ReviewDTO(Review review
					, Long reviewerId
					, String reviewerName
					, String reviewerImage) {
		super();
		this.review = review;
		this.reviewerId = reviewerId;
		this.reviewerName = reviewerName;
		this.reviewerImage = reviewerImage;
	}
	
	
	
	
	public Long getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getReviewerImage() {
		return reviewerImage;
	}
	public void setReviewerImage(String reviewImage) {
		this.reviewerImage = reviewImage;
	}
	
	
}
