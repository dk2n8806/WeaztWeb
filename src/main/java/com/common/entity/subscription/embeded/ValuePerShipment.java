package com.common.entity.subscription.embeded;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class ValuePerShipment 
{

	private int productValue;
	private int taxCost;								// The tax
	private int subscriptionValue;				// The amount the customer will pay
	private int subscriptionCost;				// The amount the merchant will get
	private int shippingCost;
	
	
	public static ValuePerShipment create(
			int productValue, int subscriptionValue, int subscriptionCost,  int shippingCost, int taxCost) {
	
		try {
			ValuePerShipment perShipment = new ValuePerShipment();
			perShipment.productValue = productValue;
			perShipment.subscriptionValue = subscriptionValue;
			perShipment.shippingCost = shippingCost;
			perShipment.taxCost = taxCost;
			perShipment.subscriptionCost = subscriptionCost;
			return perShipment;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transient
	public int totalCharge() {
		return this.subscriptionValue + shippingCost + taxCost;
	}


	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tproductValue: " + productValue + "\n\ttaxCost: " + taxCost
				+ "\n\tsubscriptionValue: " + subscriptionValue + "\n\tsubscriptionCost: " + subscriptionCost
				+ "\n\tshippingCost: " + shippingCost + "\n}";
	}
	
	
	
	

	@Column(name="PRODUCT_VALUE", nullable=false)
	public int getProductValue() {	return productValue;}
	public void setProductValue(int productValue) {this.productValue = productValue;}
	
	
	@Column(name="SUBSCRIPTION_COST", nullable=false)
	public int getSubscriptionCost() {	return subscriptionCost;}
	public void setSubscriptionCost(int subscriptionCost) {	this.subscriptionCost = subscriptionCost;}
	
	@Column(name="TAX_COST", nullable=false)
	public int getTaxCost() {	return taxCost;}
	public void setTaxCost(int taxCost) {this.taxCost = taxCost;}

	@Column(name="SUBSCRIPTION_VALUE", nullable=false)
	public int getSubscriptionValue() {	return subscriptionValue;}
	public void setSubscriptionValue(int subscriptionValue) {	this.subscriptionValue = subscriptionValue;}

	@Column(name="SHIPPING_COST", nullable=false)
	public int getShippingCost() {	return shippingCost;}
	public void setShippingCost(int shippingCost) {this.shippingCost = shippingCost;}
	
	
	
	
	
	
	
	
	
}
