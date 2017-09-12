package com.dto.product;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.service.category.CategoryService;
import com.core.service.merchant.MerchantService;
import com.core.service.product.dto.ProductTemplateDTOService;

public class TestGetProductTemplate extends BaseTest{

	@Autowired private ProductTemplateDTOService templateDTOService;
	@Autowired private MerchantService merchantService;
	@Autowired private CategoryService categoryService;
	
	private Merchant merchant;
	private Category category;
	private ProductStatus status;
	private DateInterval dateInterval;
	private Pageable pageable;
	
	
	@Before
	public void init() {
		merchant = merchantService.findById(new Long(2132016));
		category = categoryService.findById(new Long(2131995));
	}
	
	@Test
	public void test() {
		List<ProductTemplateDTO> templates = templateDTOService.getByMerchant(
													merchant, category, status, dateInterval, pageable);
		
		System.out.println("template size: " + templates.size());
//		for(ProductTemplateDTO template : templates) {
//			System.out.println(template.getId());
//		}
	}
}
