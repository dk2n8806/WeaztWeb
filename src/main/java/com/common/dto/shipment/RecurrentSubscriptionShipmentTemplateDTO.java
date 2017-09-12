package com.common.dto.shipment;

import java.io.Serializable;
import java.util.Date;


public class RecurrentSubscriptionShipmentTemplateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private String productTitle;
	private String productImage;
	private String category;

	private Date scheduledOn;
	public RecurrentSubscriptionShipmentTemplateDTO(Long productId, String productTitle,
			String productImage, String category, Date scheduledOn) {
		this.scheduledOn = scheduledOn;
		this.productId = productId;
		this.productImage = productImage;
		this.productTitle = productTitle;
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



	public void setProductTitle(String productTItle) {
		this.productTitle = productTItle;
	}



	public String getProductImage() {
		return productImage;
	}



	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}



	public Date getScheduledOn() {
		return scheduledOn;
	}
	public void setScheduledOn(Date scheduledOn) {
		this.scheduledOn = scheduledOn;
	}

}
