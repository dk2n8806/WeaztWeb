package com.common.dto.order;

import java.util.Date;

import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.type.SubscriptionStatus;

/**
 * <p>Template to display the accounts that request the orders</p>
 * 
 * @author dk
 *
 */
public class CustomerOrderIntentTemplateDTO {
	
	private OrderIntent orderIntent;
	private Product product;
	private Long subscriptionId;
	private String customerName;
	private String customerImage;
	private int subscriptionCost;
	private SubscriptionStatus subscriptionStatus;
	private Date scheduledOn;
	
	





	public CustomerOrderIntentTemplateDTO(OrderIntent orderIntent
									, Product product
									, Long subscriptionId
									, String customerName
									, String customerImage
									, int subscriptionCost
									, SubscriptionStatus subscriptionStatus
									, Date scheduledOn
									) {
		super();
		this.orderIntent = orderIntent;
		this.product = product;
		this.subscriptionId = subscriptionId;
		this.customerName = customerName;
		this.customerImage = customerImage;
		this.subscriptionCost = subscriptionCost;
		this.subscriptionStatus = subscriptionStatus;
		this.scheduledOn = scheduledOn;
	}


	
	public int getSubscriptionCost() {
		return subscriptionCost;
	}
	public void setSubscriptionCost(int subscriptionCost) {
		this.subscriptionCost = subscriptionCost;
	}
	public OrderIntent getOrderIntent() {
		return orderIntent;
	}


	public void setOrderIntent(OrderIntent orderIntent) {
		this.orderIntent = orderIntent;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Long getSubscriptionId() {
		return subscriptionId;
	}


	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerImage() {
		return customerImage;
	}


	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
	}


	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}


	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}


	public Date getScheduledOn() {
		return scheduledOn;
	}


	public void setScheduledOn(Date scheduledOn) {
		this.scheduledOn = scheduledOn;
	}



	
	


}
