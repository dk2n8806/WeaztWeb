package com.common.calc;

public class ProductCalculatorResult {

	private int productPrice;
	private int subscriptionPrice;
	private int subscriptionCost;
	private int shippingCost;
	private int taxCost;
	private int saveRate;
	
	
	public ProductCalculatorResult(int productPrice, int subscriptionPrice,
			int subscriptionCost, int saveRate, int shippingCost, int taxCost) {
		super();
		this.productPrice = productPrice;
		this.subscriptionPrice = subscriptionPrice;
		this.subscriptionCost = subscriptionCost;
		this.shippingCost = shippingCost;
		this.taxCost = taxCost;
		this.saveRate = saveRate;
	}
	
	
	public int getSaveRate() {
		return saveRate;
	}
	
	
	public int getProductPrice() {
		return productPrice;
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public int getSubscriptionCost() {
		return subscriptionCost;
	}
	public int getSubscriptionPrice() {
		return subscriptionPrice;
	}
	public int getTaxCost() {
		return taxCost;
	}



	@Override
	public String toString() {
		return "ProductCalculatorResult [\n\tproductPrice=" + productPrice
				+ "\n\t, subscriptionPrice=" + subscriptionPrice
				+ "\n\t, subscriptionCost=" + subscriptionCost 
				+ "\n\t, shippingCost="+ shippingCost 
				+ "\n\t, taxCost=" + taxCost 
				+ "\n\t]";
	}

	
	
	

	
	
}
