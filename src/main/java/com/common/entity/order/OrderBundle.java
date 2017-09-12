package com.common.entity.order;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.product.Product;
import com.common.type.OrderStatus;
import com.common.type.ProductStatus;

@Entity
@Table(name="ORDER_BUNDLE")
public class OrderBundle extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

	private Product product;
	private int collectedAmount;
	private int serviceFee;
	private OrderStatus status;
	private int noo;													// number of order in the bundle
	
	private List<OrderTransaction> orderTransactions;
	
	

	
    public static OrderBundle create(Product product, List<OrderTransaction> orderTransactions) 
    {
    	
    	try {
    		if(product.getStatus().equals(ProductStatus.DELETED))
    			throw new IllegalArgumentException("Invalid product status");
    		if(orderTransactions.size() == 0)
    			throw new IllegalArgumentException("number of order transaction must be > 0");
    		
    		int amount = 0;
    		
    		
        	OrderBundle order = new OrderBundle();
        	for(OrderTransaction transaction : orderTransactions) {
    			amount += transaction.getTotal();
    			transaction.setOrderBundle(order);
        	}
        	
    		if(amount <= 0)
    			throw new IllegalArgumentException("amount must be > 0");
        	order.product 			= product;
        	order.status 			= OrderStatus.PENDING;
        	order.noo 				= orderTransactions.size();
        	order.collectedAmount = amount;
        	order.orderTransactions = orderTransactions;
        	
        	return order;
    	} catch(IllegalArgumentException e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	
	

	

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="orderBundle")
    public List<OrderTransaction> getOrderTransactions() {		return orderTransactions;}
    public void setOrderTransactions(List<OrderTransaction> orderDetails) {		this.orderTransactions = orderDetails;}
 

    @Column(name="SERVICE_FEE", nullable=false)
    public int getServiceFee() {	return serviceFee;}
    public void setServiceFee(int serviceFee) {	this.serviceFee = serviceFee;}
    
    
    @Column(name="COLLECTED_AMOUNT", nullable=false, updatable=false)
    public int getCollectedAmount() {		return collectedAmount;	}
    public void setCollectedAmount(int collectedAmount) {	this.collectedAmount = collectedAmount;}
    
    
    @Column(name="NOO")
    public int getNoo() {	return noo;}
    public void setNoo(int noo) {	this.noo = noo;}
 

	@Enumerated(EnumType.STRING)
	@Column(name="ORDER_STATUS", nullable=false)
	public OrderStatus getStatus() {	return status;}
	public void setStatus(OrderStatus orderStatus) {		this.status = orderStatus;}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID", nullable=false)
	public Product getProduct() {	return product;}
	public void setProduct(Product product) {		this.product = product;}
	
}
