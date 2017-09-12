package com.dto.orderIntnet.customer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.dto.order.CustomerOrderIntentTemplateDTO;
import com.common.entity.product.Product;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.service.order.dto.OrderIntentTemplateDTOService;
import com.core.service.product.ProductService;

public class TestGetCustomerOrderIntent extends BaseTest{

	@Autowired private OrderIntentTemplateDTOService templateService;
	@Autowired private ProductService productService;
	
	private Product product;
	private OrderIntentStatus status;
	private DateInterval dateInterval;
	private Pageable pageable;
	
	@Before
	public void init() {
		product = productService.findById(new Long(32));
		
		status = OrderIntentStatus.REQUESTING;
		dateInterval = null;
		pageable = null;
	}
	
	@Test
	public void test() {
		List<CustomerOrderIntentTemplateDTO> customers = templateService.getByProduct(
				product, status, dateInterval, pageable);
		
		System.out.println(customers.size());
	}
}
