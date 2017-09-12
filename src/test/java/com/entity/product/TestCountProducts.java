package com.entity.product;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.category.Category;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.service.product.ProductService;

public class TestCountProducts extends BaseTest{

	@Autowired private ProductService productService;
	private Category category;
	private ProductStatus status;
	private DateInterval dateInterval;
	
	
	@Before
	public void init() {
		category = null;
		status = ProductStatus.PUBLIC;
		dateInterval = null;
	}
	
	@Test
	public void test() {
		System.out.println(productService.countProducts(category, status, dateInterval));
	}
}
