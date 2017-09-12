package com.entity.product.gallery;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.core.service.product.ProductGalleryService;
import com.core.service.product.ProductService;

public class TestGetByProduct extends BaseTest{

	@Autowired private ProductGalleryService galleryService;
	@Autowired private ProductService productService;
	
	
	private Product product;
	
	@Before
	public void init() {
		product = productService.getReference(new Long(71));
	}
	
	@Test
	public void test() {
		List<ProductGallery> gallery = galleryService.getByProduct(product, true);
		System.out.println(gallery.size());
		for(ProductGallery p : gallery) {
			System.out.println(p.getId() + " " + p.getProduct().getId() + " " + p.getImagePath().isActive());
		}
	}
	
	
}