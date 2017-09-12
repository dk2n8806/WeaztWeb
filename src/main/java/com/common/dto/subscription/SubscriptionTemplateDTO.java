package com.common.dto.subscription;

import java.io.Serializable;
import java.util.Date;

import com.common.type.SubscriptionStatus;

public class SubscriptionTemplateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long subscriptionId;
	private String productTitle;
	private String productImage;
	private String category;
	private int subscriptionValue;
	private SubscriptionStatus status;
	private int hadShipped;
	private int nos;
	private Date scheduledOn;
	
	

	public SubscriptionTemplateDTO(
			Long subscriptionId
			, String productTitle
			, String productImage
			, String category
			, int subscriptionValue
			, SubscriptionStatus status
			, int hadShipped
			, int nos
			, Date scheduledOn) {
		super();
		this.subscriptionId = subscriptionId;
		this.productTitle = productTitle;
		this.productImage = productImage;
		this.category = category;
		this.subscriptionValue = subscriptionValue;
		this.status = status;
		this.hadShipped = hadShipped;
		this.nos = nos;
		this.scheduledOn = scheduledOn;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
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

	public int getSubscriptionValue() {
		return subscriptionValue;
	}

	public void setSubscriptionValue(int subscriptionValue) {
		this.subscriptionValue = subscriptionValue;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public int getHadShipped() {
		return hadShipped;
	}

	public void setHadShipped(int hadShipped) {
		this.hadShipped = hadShipped;
	}

	public int getNos() {
		return nos;
	}

	public void setNos(int nos) {
		this.nos = nos;
	}

	public Date getScheduledOn() {
		return scheduledOn;
	}

	public void setScheduledOn(Date scheduledOn) {
		this.scheduledOn = scheduledOn;
	}

	
	
	
	
	
	
	
	
}
