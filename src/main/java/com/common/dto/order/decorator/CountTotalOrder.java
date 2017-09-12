package com.common.dto.order.decorator;

import java.util.List;

import com.common.dto.order.OrderIntentTemplateDTO;

public interface CountTotalOrder {

	/** @see OrderTemplateAggregate#counTotalOrder(List)
	 */
	List<OrderTemplateAggregate>
	counTotalOrder(List<OrderIntentTemplateDTO> orderList);
}
