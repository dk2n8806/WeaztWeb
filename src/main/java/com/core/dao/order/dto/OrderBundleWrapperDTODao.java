package com.core.dao.order.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.entity.merchant.Merchant;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface OrderBundleWrapperDTODao 
	extends GenericRepository<OrderBundleWrapperDTO, Long>
{

	/** {@link OrderBundleWrapperDTODaoImpl#getByMerchant(Long, Merchant)} */
	OrderBundleWrapperDTO getByMerchant(Long orderBundleId, Merchant merchant);
	
	/** {@link OrderBundleWrapperDTODaoImpl#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)} */
	List<OrderBundleWrapperDTO> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable);
}
