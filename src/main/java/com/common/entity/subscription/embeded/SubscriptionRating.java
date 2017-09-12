package com.common.entity.subscription.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class SubscriptionRating {

	
	private boolean isRecommended;
	private boolean isFavorited;
	

	public static SubscriptionRating create() {
		SubscriptionRating rating = new SubscriptionRating();
		rating.isFavorited = false;
		rating.isRecommended=false;
		return rating;
	}
	
	
	@Column(name="IS_RECOMMENDED")
	public boolean isRecommended() {	return isRecommended;}
	public void setRecommended(boolean isRecommended) {	this.isRecommended = isRecommended;}
	
	@Column(name="IS_FAVORITED")
	public boolean isFavorited() {	return isFavorited;}
	public void setFavorited(boolean isFavorited) {	this.isFavorited = isFavorited;}
	
	

	
	
}
