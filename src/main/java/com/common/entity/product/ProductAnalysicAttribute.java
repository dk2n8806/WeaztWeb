package com.common.entity.product;

public class ProductAnalysicAttribute {

	private int numSubscribers;
	private int numReviews;
	private int numFavorited;
	private int numRecommended;
	private int completedShipment;
	private int remainingShipment;
	private int totalRevenue;
	
	public static ProductAnalysicAttribute create() {
		ProductAnalysicAttribute attribute = new ProductAnalysicAttribute();
		attribute.numSubscribers = 0;
		attribute.numReviews = 0;
		attribute.numFavorited = 0;
		attribute.numRecommended = 0;
		attribute.completedShipment = 0;
		attribute.remainingShipment = 0;
		attribute.totalRevenue = 0;
		return attribute;
	}

	public int getNumSubscribers() {return numSubscribers;}
	public void setNumSubscribers(int numSubscribers) {this.numSubscribers = numSubscribers;}

	public int getNumReviews() {return numReviews;}
	public void setNumReviews(int numReviews) {this.numReviews = numReviews;}

	public int getNumFavorited() {return numFavorited;}
	public void setNumFavorited(int numFavorited) {this.numFavorited = numFavorited;}

	public int getNumRecommended() {return numRecommended;}
	public void setNumRecommended(int numRecommended) {this.numRecommended = numRecommended;}

	public int getCompletedShipment() {return completedShipment;}
	public void setCompletedShipment(int completedShipment) {this.completedShipment = completedShipment;}

	public int getRemainingShipment() {return remainingShipment;}
	public void setRemainingShipment(int remainingShipment) {this.remainingShipment = remainingShipment;}

	public int getTotalRevenue() {return totalRevenue;}
	public void setTotalRevenue(int totalRevenue) {this.totalRevenue = totalRevenue;}
	
	
	
}
