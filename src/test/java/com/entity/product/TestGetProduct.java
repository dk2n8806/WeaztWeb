package com.entity.product;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.core.service.product.ProductService;

public class TestGetProduct extends BaseTest{

	@Autowired private ProductService productService;
	
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testGetById(){
		Product product = productService.findById(new Long(2132028));
		assertNotNull(product);
	}
	
	
	
	@Test
	public void testProducts() {
		List<Product> products = productService.getProducts(null, ProductStatus.PUBLIC, null, new PageSearch(0,  10));
		
		System.out.println(products.size());
		
		for(Product product : products) {
			System.out.println("product id =" + product.getId() + "\n image = " + product.getDisplayImage().getPath() );
		}
	}
	
}

