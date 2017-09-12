package com.entity.product;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.service.merchant.MerchantService;
import com.core.service.product.ProductService;

public class TestGetByMerchant extends BaseTest{

	@Autowired private ProductService productService;
	@Autowired private MerchantService merchantService;
	Merchant merchant;
	private Category category;
	private ProductStatus status = ProductStatus.PUBLIC;
	private DateInterval dateInterval;
	
	@Before
	public void init() {
		merchant = merchantService.getReference(new Long(17));
	}
	
	@Test
	public void test() {
		long count = productService.countByMerchant(merchant, category, status, dateInterval);
		System.out.println(count);
	}
}
