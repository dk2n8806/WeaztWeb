package com.core.service.order.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.order.OrderTransactionWrapperDTO;
import com.common.entity.order.OrderBundle;
import com.core.dao.order.dto.OrderTransactionWrapperDTODao;

@Service
@Transactional(readOnly=true)
public class OrderTransactionWrapperDTOSerivceImpl
	implements OrderTransactionWrapperDTOSerivce
{

	@Autowired private OrderTransactionWrapperDTODao dao;

	
	/** :::
	 * {@link OrderTransactionWrapperDTOSerivce#getByOrder(OrderBundle)}
	 * */
	@Override
	public List<OrderTransactionWrapperDTO> getByOrder(OrderBundle orderBundle) {
		if(orderBundle == null) return new ArrayList<OrderTransactionWrapperDTO>();
		return dao.getByOrder(orderBundle);
	}
	
}
