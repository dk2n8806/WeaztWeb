package com.common.entity.order;

import java.util.List;

public class OrderBundleCollection {

	private long size;
	private List<OrderBundle> orderBundles;
	
	
	
	public OrderBundleCollection(long size, List<OrderBundle> orderBundles) {
		super();
		this.size = size;
		this.orderBundles = orderBundles;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public List<OrderBundle> getOrderBundles() {
		return orderBundles;
	}
	public void setOrderBundles(List<OrderBundle> orderBundles) {
		this.orderBundles = orderBundles;
	}
	
	
}
