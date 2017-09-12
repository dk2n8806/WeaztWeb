package com.common.wrapper;

import java.io.Serializable;

import com.common.entity.product.Product;

public class ProductSubscriptionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private long totalSubscription;
	
	public ProductSubscriptionWrapper(Product product, long totalSubscription) {
		this.product = product;
		this.totalSubscription =totalSubscription;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getTotalSubscription() {
		return totalSubscription;
	}
	public void setTotalSubscription(long totalSubscription) {
		this.totalSubscription = totalSubscription;
	}
}
