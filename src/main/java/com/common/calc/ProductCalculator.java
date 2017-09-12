package com.common.calc;

import com.common.NoShipmentRule;
import com.common.entity.merchant.TaxRate;
import com.common.entity.product.Product;
import com.common.entity.promo.Promotion;

public class ProductCalculator {

	private Promotion promotion;
	private Product product;
	private TaxRate taxRate;
	private int shippingCost;
	private int nos;
	
	
	
	
	
	public ProductCalculator(Product product
						, int nos, int shipmentCost
						, TaxRate taxRate, Promotion promotion) 
	{
		this.product = product;
		this.taxRate = taxRate;
		this.promotion = promotion;
		this.nos = nos;
		this.shippingCost 	= shipmentCost;
	}
	
	
	public ProductCalculatorResult getResult() {
		if(this.product == null) 
			throw new IllegalArgumentException("product is required");
		if(this.shippingCost < 0) 
			throw new IllegalArgumentException("Shipping_cost must be >= 0");
		if(!NoShipmentRule.isValidated(nos)) 
			throw new IllegalArgumentException("NOS (number of shipment) must be between [2-9]");
		

		return  new ProductCalculatorResult(this.product.getBasicInfo().getPrice()
											, getSubscriptionPrice()
											, getSubscriptionCost()
											, getSaveRate() 
											, shippingCost
											, getTaxCost());
	}
	
	

	
	/** :::
	 * <h1>Subscripiton cost</h1>
	 * 
	 * <p>Calculate the amount the seller will earn everytime processing shipment.</p>
	 * 
	 * The amount is calculated depending on the subscription save_rate of the customer.
	 * 
	 ** ::: */
	private int getSubscriptionCost() {
		int productPrice = this.product.getBasicInfo().getPrice();
		int promotionRate = 0;
		if(this.nos >= this.product.getSubscriptionInfo().getNos()) {
			promotionRate = this.product.getSubscriptionInfo().getPercentSave();
		}
		int subscriptionPrice = productPrice - (productPrice * promotionRate) / 10000;
		return subscriptionPrice + shippingCost + getTaxCost();
	}
	
	
	/** :::
	 * <h1>Subscripiton Price</h1>
	 * 
	 * Calculate the amount the customer subscribed at
	 ** ::: */
	private int getSubscriptionPrice() {
		int productPrice = this.product.getBasicInfo().getPrice();
		int promotionRate = 0;// promotion != null ? promotion.getValue() : 0;
		
		// Apply promotion if the client select nos more or equal to default_nos

		//System.out.println("Promotion rate (0): " + promotionRate);
		if(this.nos >= this.product.getSubscriptionInfo().getNos()) {
			promotionRate = this.product.getSubscriptionInfo().getPercentSave();
			
			

			//System.out.println("Promotion rate (1): " + promotionRate);
			// ::: Apply system promotion
			if(promotion != null && promotion.isAppliable()) {
				promotionRate += promotion.getValue();
			}

			//System.out.println("Promotion rate (2): " + promotionRate);
		}
		

		//System.out.println("Promotion rate (3): " + promotionRate);
		int subscriptionPrice = productPrice - (productPrice * promotionRate) / 10000;
		//System.out.println("Subscription price: " + subscriptionPrice);
		return subscriptionPrice;
		
		
	}
	
	
	/** :::
	 ** ::: */
	private int getSaveRate() {
		int promotionRate = 0;
		if(this.nos >= this.product.getSubscriptionInfo().getNos()) {
			promotionRate = this.product.getSubscriptionInfo().getPercentSave();
		}
		if(promotion != null) {
			promotionRate += promotion.getValue();
		}
		return promotionRate;
	}
	

	
	/** :::
	 ** ::: */
	private int getTaxCost() {
		if(taxRate == null) {
			return 0;
		} 
		
		return product.getBasicInfo().getPrice() * taxRate.getRateValue() / 10000;
	}
	
	
}
