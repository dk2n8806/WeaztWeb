package com.entity.product;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.exception.ProductException;
import com.common.type.ProductStatus;
import com.core.service.product.ProductService;

public class TestUpdateStatus extends BaseTest{

	@Autowired private ProductService productService;
	private ProductStatus status;
	private Product product;
	
	
	@Before
	public void init() {
		product = productService.findById(new Long(59));
		status = ProductStatus.PUBLIC;
	}
	
	@Test
	public void test() throws ProductException {
		productService.makePublic(product);
		
		assertEquals(product.getStatus(), status);
		assertEquals(productService.findById(product.getId()).getStatus(), status);
	}


}
