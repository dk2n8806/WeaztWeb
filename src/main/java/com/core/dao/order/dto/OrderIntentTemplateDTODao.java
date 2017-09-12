package com.core.dao.order.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.order.CustomerOrderIntentTemplateDTO;
import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;

public interface OrderIntentTemplateDTODao {


	/** {@link OrderIntentTemplateDTODaoImpl#getTemplates} */
	List<OrderIntentTemplateDTO> getTemplates(Merchant merchant
			, OrderIntentStatus orderStatus, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link OrderIntentTemplateDTODaoImpl#getCustomerTemplates} */
	List<CustomerOrderIntentTemplateDTO> getCustomerTemplates(
			Product product, OrderIntentStatus orderStatus, DateInterval dateInterval, Pageable pageable);

}
