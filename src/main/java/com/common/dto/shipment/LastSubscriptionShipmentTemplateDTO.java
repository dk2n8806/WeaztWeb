package com.common.dto.shipment;

import java.io.Serializable;

import com.common.entity.order.Shipment;

public class LastSubscriptionShipmentTemplateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Shipment shipment;
	private String productTitle;
	private String productImage;
	private Long productId;
	private String category;
	
	public LastSubscriptionShipmentTemplateDTO(Long productId
						, String productTitle
						, String productImage
						, String category
						, Shipment shipment) 
	{
		this.shipment = shipment;
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



	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
}
