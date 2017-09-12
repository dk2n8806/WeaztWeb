package com.core.service.order.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.entity.merchant.Merchant;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;

public interface OrderBundleWrapperDTOService {

	
	/** {@link OrderBundleWrapperDTOServiceImpl#getByMerchant(Long, Merchant)} */
	OrderBundleWrapperDTO getByMerchant(Long orderBundleId, Merchant merchant);
	
	/** {@link OrderBundleWrapperDTOServiceImpl#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)} */
	public List<OrderBundleWrapperDTO> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable);
}
