package com.core.service.order.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.entity.merchant.Merchant;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.dto.OrderBundleWrapperDTODao;

@Service
@Transactional(readOnly=true)
public class OrderBundleWrapperDTOServiceImpl implements OrderBundleWrapperDTOService {

	@Autowired private OrderBundleWrapperDTODao wrapperDao;;
	
	
	
	/** :::
	 * 
	 * 
	 * {@link OrderBundleWrapperDTOService#getByMerchant(Merchant, OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderBundleWrapperDTO> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) return new ArrayList<>();
		return wrapperDao.getByMerchant(merchant, status, dateInterval, pageable);
	}



	/** :::
	 * {@link OrderBundleWrapperDTOService#getByMerchant(Long, Merchant)}
	 */
	@Override
	public OrderBundleWrapperDTO getByMerchant(Long orderBundleId, Merchant merchant) {
		try {
			return wrapperDao.getByMerchant(orderBundleId, merchant);
		} catch(NoResultException e){
			return null;
		}
	}

}
