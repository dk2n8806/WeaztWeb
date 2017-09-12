package com.entity.product.gallery;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.support.embeded.ImagePath;
import com.core.service.product.ProductGalleryService;
import com.core.service.product.ProductService;

public class TestCreateProductGallery extends BaseTest {

	@Autowired private ProductService productService;
	@Autowired private ProductGalleryService galleryService;
	private ImagePath imagePath;
	private Product product;
	
	
	@Before
	public void init() {
		product = productService.getReference(new Long(59));
		System.out.println("product id: " + product.getId());
		imagePath = ImagePath.create("image path example");
	}
	
	@Test
	public void test() {
		ProductGallery gallery = galleryService.save(imagePath, product);
		
		System.out.println(gallery.getId());
	}
}
