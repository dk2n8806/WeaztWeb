package com.entity.product.gallery;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.core.service.product.ProductGalleryService;
import com.core.service.product.ProductService;

public class TestDeactivePhoto extends BaseTest{

	@Autowired private ProductGalleryService galleryService;
	@Autowired private ProductService productService;
	
	
	private Long galleryId;
	private Product product;
	
	@Before
	public void init() {
		galleryId = new Long(74);
		product = productService.getReference(new Long(65));
	}
	
	@Test
	public void test() {
		galleryService.deactive(galleryId, product);
	}
	
	
}
