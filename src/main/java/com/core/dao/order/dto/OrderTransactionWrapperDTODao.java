package com.core.dao.order.dto;

import java.util.List;

import com.common.dto.order.OrderTransactionWrapperDTO;
import com.common.entity.order.OrderBundle;
import com.core.dao.generic.GenericRepository;

public interface OrderTransactionWrapperDTODao
extends GenericRepository<OrderTransactionWrapperDTO, Long>
{

	/** {@link OrderTransactionWrapperDTODaoImpl#getByOrder(OrderBundle)} */
	List<OrderTransactionWrapperDTO> getByOrder(OrderBundle orderBundle);
}
