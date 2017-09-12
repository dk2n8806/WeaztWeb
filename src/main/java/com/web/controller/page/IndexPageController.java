package com.web.controller.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.entity.tracking.IndexPageTracker;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.category.CategoryService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.core.service.tracker.PageTrackerService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


@Controller
public class IndexPageController {

	@Autowired private CategoryService categoryService;
	@Autowired private ProductTemplateDTOService templateService;
	@Autowired private PageTrackerService pageTrackerService;

	
	
	@RequestMapping(value={UriPageRequestMapping.BASE, UriPageRequestMapping.Shop.SHOP}
					, method=RequestMethod.GET)
	public String index(Model model
						, @ActiveUserPrincipal Account account
						, HttpServletRequest request)
	
	{
		List<ProductTemplateDTO> newProducts = new ArrayList<>();
		final Category category = null;
		final DateInterval dateInterval = null;
		final ProductStatus status = ProductStatus.PUBLIC;

		newProducts = templateService.getTemplates(
								category, status, dateInterval, new PageSearch(0, 25));
		
		Map<String, List<ProductTemplateDTO>> categories = new HashMap<>();
		categories.put("coffee", getByCategory("coffee"));
		categories.put("tea"		, getByCategory("tea"));
		
		// Add index page tracker
		pageTrackerService.save(IndexPageTracker.create(account, request.getRemoteAddr()));
		model.addAttribute("categories", categories);
		model.addAttribute("products", newProducts);
		return PageAdvice.Shop.INDEX;
	}
	
	
	private List<ProductTemplateDTO> getByCategory(String name) {
		Category category = categoryService.getByName(name);
		return templateService.getTemplates(category, ProductStatus.PUBLIC, null, new PageSearch(0, 5));
	}
	
	
	
}
