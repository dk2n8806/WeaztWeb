package com.common.dto.order.decorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.dto.order.decorator.CountTotalOrder;

public class OrderTemplateAggregate implements CountTotalOrder {
	
	private OrderIntentTemplateDTO orderSnapshot;
	private int orderCount;
	private int totalRevenue;
	
	public OrderTemplateAggregate() {}
	
	
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public OrderIntentTemplateDTO getOrderSnapshot() {
		return orderSnapshot;
	}
	public void setOrderSnapshot(OrderIntentTemplateDTO orderSnapshot) {
		this.orderSnapshot = orderSnapshot;
	}
	
	public int getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}


	
	
	@Override
	public List<OrderTemplateAggregate> 
	counTotalOrder(List<OrderIntentTemplateDTO> orderList) {
		return this.listCount(this.mapCount(orderList));
	}
	
	
	
	private Map<Long, OrderTemplateAggregate> mapCount(List<OrderIntentTemplateDTO> orders) {
		Map<Long, OrderTemplateAggregate> map = 
				new HashMap<Long, OrderTemplateAggregate>();
		OrderTemplateAggregate aggr;
		for(OrderIntentTemplateDTO o : orders) {
			if(map.containsKey(o.getProductId())) {
				aggr = map.get(o.getProductId());
				//aggr.setOrderCount(aggr.getOrderCount() + 1);
				
				++aggr.orderCount; 
				aggr.totalRevenue += o.getSubscriptionTotal();	
			} else {
				aggr = new OrderTemplateAggregate();
				aggr.orderCount = 1;
				aggr.totalRevenue = o.getSubscriptionTotal();
				aggr.orderSnapshot = o;
				//aggr.setOrderCount(1);
				//aggr.setTotalRevenue(o.getSubscriptionTotal());
				//aggr.setOrderSnapshot(o);
			}
			map.put(o.getProductId(), aggr);
		}
		return map;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	private List<OrderTemplateAggregate> listCount(Map mapCount) {
		List<OrderTemplateAggregate> countOrders = new ArrayList<OrderTemplateAggregate>();
		Iterator it = mapCount.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        countOrders.add((OrderTemplateAggregate) pair.getValue());
	    }
		return countOrders;
	}


	@Override
	public String toString() {
		return new StringBuilder("OrderTemplateAggregate")
				.append("\n").append("product_id: ").append(this.orderSnapshot.getProductId())
				.append("\n").append("order_count:").append(this.orderCount)
				.append("\n").append("total_revenue:").append(this.totalRevenue)
				.toString();
	}
	
	
}
