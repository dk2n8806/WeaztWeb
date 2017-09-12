package com.core.service.order.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.order.CustomerOrderIntentTemplateDTO;


import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;



public interface OrderIntentTemplateDTOService {

	/** {@link OrderIntentTemplateDTOServiceImpl#getTemplates(OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntentTemplateDTO> getTemplates(OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentTemplateDTOServiceImpl#getByMerchant(Merchant, OrderIntentStatus, DateInterval, Pageable)} */
	List<OrderIntentTemplateDTO> getByMerchant(
			Merchant merchant, OrderIntentStatus status, DateInterval dateInterval, Pageable page);

	
	/** {@link OrderIntentTemplateDTOServiceImpl#getByProduct(Product, OrderIntentStatus, DateInterval, Pageable)} */
	List<CustomerOrderIntentTemplateDTO> getByProduct(
			Product product, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link OrderIntentTemplateDTOServiceImpl#getCustomerTemplates(OrderIntentStatus, DateInterval, Pageable)} */
	List<CustomerOrderIntentTemplateDTO> getCustomerTemplates(OrderIntentStatus status, DateInterval dateInterval, Pageable pageable);
}
