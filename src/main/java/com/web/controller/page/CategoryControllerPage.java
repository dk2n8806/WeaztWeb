package com.web.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.category.CategoryService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.web.advice.PageAdvice;

@Controller
public class CategoryControllerPage {

	@Autowired private CategoryService categoryService;
	@Autowired private ProductTemplateDTOService productTemplateService;
	
	
	/** 
	 * <p>Show products requesting by a client regarding a particular category</p>
	 */
	@RequestMapping(value="/category/{catName}", method=RequestMethod.GET)
	public String showPage(Model model
								, @ActiveUserPrincipal Account accout
								, @PathVariable String catName
								, @RequestParam(name="p", defaultValue="0") int pageNumber
								, @RequestParam(name="s", defaultValue="30") int pageSize) 
	{	
		
		String searchQuery = getCategoryName(catName);
		Category category = categoryService.getByName(searchQuery);
		Assert.notNull(category, "category can't be null");

		DateInterval dateInterval = null;
		ProductStatus status = ProductStatus.PUBLIC;
		Pageable pageable = new PageSearch(pageNumber, pageSize);

		Map<String, List<ProductTemplateDTO>> products = 
						new HashMap<String, List<ProductTemplateDTO>>();
		products.put(category.getName()
				, productTemplateService.getTemplates(category, status, dateInterval, pageable));
		
		model.addAttribute("searchQuery", searchQuery + " subscriptions");
		model.addAttribute("page", pageable);
		model.addAttribute("products", products);
		return PageAdvice.Shop.SHOP;
	}
	
	private String getCategoryName(String categaory) {
		return categaory.replace("-", " ").replace("+", " & ");
	}

	
	
	
	
	
	
}
