package com.common.dto.order;


import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;


public class OrderIntentTemplateDTO {
	
	
	private Long orderIntentId;
	private Long productId;
	private String productTitle;
	private String productImage;
	private String productCategory;
	private ProductStatus productStatus;
	private int subscriptionTotal;
	private SubscriptionStatus subscriptionStatus;
	
	
	
	public OrderIntentTemplateDTO(Long orderIntentId
							 , Long productId
							 , String productTitle
							 , String productImage
							 , String productCategory
							 , ProductStatus productStatus
							 , int subscriptionTotal
							 , SubscriptionStatus subscriptionStatus) {
		super();
		this.orderIntentId = orderIntentId;
		this.productId = productId;
		this.productImage = productImage;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.productStatus = productStatus;
		this.subscriptionTotal = subscriptionTotal;
		this.subscriptionStatus = subscriptionStatus;
	}

	public String getProductCategory() {		return productCategory;	}
	public void setProductCategory(String productCategory) {	this.productCategory = productCategory;}

	public Long getOrderIntentId() {		return orderIntentId;	}
	public void setOrderIntentId(Long orderIntentId) {	this.orderIntentId = orderIntentId;}
	
	public ProductStatus getProductStatus() {		return productStatus;}
	public void setProductStatus(ProductStatus productStatus) {	this.productStatus = productStatus;}

	
	public Long getProductId() {		return productId;}
	public void setProductId(Long productId) {		this.productId = productId;}

	public String getProductImage() {		return productImage;}
	public void setProductImage(String productImage) {		this.productImage = productImage;}



	public String getProductTitle() {		return productTitle;}
	public void setProductTitle(String productTitle) {		this.productTitle = productTitle;}



	public int getSubscriptionTotal() {		return subscriptionTotal;	}
	public void setSubscriptionTotal(int subscriptionTotal) {	this.subscriptionTotal = subscriptionTotal;}


	public SubscriptionStatus getSubscriptionStatus() {		return subscriptionStatus;}
	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {		this.subscriptionStatus = subscriptionStatus;}
	
	

	
	
	
	
	
	
	
	
	
	
	

}
