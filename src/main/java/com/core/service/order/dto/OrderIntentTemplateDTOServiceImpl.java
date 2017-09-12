package com.core.service.order.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.order.CustomerOrderIntentTemplateDTO;
import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.dto.OrderIntentTemplateDTODao;


@Service
@Transactional(readOnly=true)
public class OrderIntentTemplateDTOServiceImpl
implements OrderIntentTemplateDTOService {

	@Autowired private OrderIntentTemplateDTODao templateDao;

		
	/** :::
	 * <p>Retrieve a list of order_template by merchant</p>
	 * 
	 * {@link OrderIntentTemplateDTODao#getTemplates(Merchant, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntentTemplateDTO> getByMerchant(Merchant merchant,
			OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) {
			return new ArrayList<OrderIntentTemplateDTO>();
		}
		return templateDao.getTemplates(merchant, status, dateInterval, pageable);
		
	}

		
	/**
	 * <p>Retrieve a list of customer_order template on the product</p>
	 * @return the order accounts
	 * 
	 * {@link OrderIntentTemplateDTODao#getCustomerTemplates(Product, OrderIntentStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<CustomerOrderIntentTemplateDTO> getByProduct(Product product,
			OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) {
		if(product == null) {
			return new ArrayList<CustomerOrderIntentTemplateDTO>();
		}
		return templateDao.getCustomerTemplates(product, status, dateInterval, pageable);
	}

	
/**
 * Retrieve a list of customer_order templates
 * 
 * {@link OrderIntentTemplateDTOService#getCustomerTemplates(OrderIntentStatus, DateInterval, Pageable)}
 */
	@Override
	public List<CustomerOrderIntentTemplateDTO> getCustomerTemplates(
			OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) {
		return templateDao.getCustomerTemplates(null, status, dateInterval, pageable);
	}

	
	
/****************************************
 * retrieve a list of order_intent_template
 * 
 * {@link OrderIntentTemplateDTOService#getTemplates(OrderIntentStatus, DateInterval, Pageable)}
 */
	@Override
	public List<OrderIntentTemplateDTO> getTemplates(OrderIntentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		return templateDao.getTemplates(null, status, dateInterval, pageable);
	}
	

}
