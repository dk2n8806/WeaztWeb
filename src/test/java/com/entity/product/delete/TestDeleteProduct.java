package com.entity.product.delete;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderIntentService;
import com.core.service.order.dto.OrderIntentTemplateDTOService;
import com.core.service.process.IDeleteProductProcessService;
import com.core.service.product.ProductService;

public class TestDeleteProduct extends BaseTest{

	@Autowired private IDeleteProductProcessService deleteProductService;
	@Autowired private ProductService productService;
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentService;

	@Autowired private OrderIntentTemplateDTOService templateService;
	
	
	@Test
	public void test() throws ProductException, SubscriptionException, OrderException {
		Product product = productService.findById(new Long(2132113));
		
		deleteProductService.delete(product);
	}
	
}
