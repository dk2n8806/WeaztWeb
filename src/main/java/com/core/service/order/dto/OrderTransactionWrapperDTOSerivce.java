package com.core.service.order.dto;

import java.util.List;

import com.common.dto.order.OrderTransactionWrapperDTO;
import com.common.entity.order.OrderBundle;

public interface OrderTransactionWrapperDTOSerivce {

	/** ::: {@link OrderTransactionWrapperDTOSerivceImpl#getByOrder(OrderBundle)} */
	List<OrderTransactionWrapperDTO> getByOrder(OrderBundle orderBundle);
}
