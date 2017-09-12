package com.web.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.web.advice.PageAdvice;


@Controller
public class BrowsePageController
{

	
	@Autowired private ProductTemplateDTOService templateService;
	
		
	/** :::
	 * <p>Display a list of products</p>
	 * 
	 * {@link IBrowsePageAdvise#showIndexPage}
	 * ::: */
	@RequestMapping(value="/shop/{search}", method=RequestMethod.GET)
	public String showIndexPage(Model model
													, @PathVariable String search
													, @RequestParam(name="p", defaultValue="0") int pageNumber
													, @RequestParam(name="s", defaultValue="30") int pageSize) 
	{
		Map<String, List<ProductTemplateDTO>> products = 
						new HashMap<String, List<ProductTemplateDTO>>();
		String searchQuery = "";
		final Pageable pageable = new PageSearch(pageNumber, pageSize);
		if(search.toLowerCase().equals("new-subscriptions")) {
			searchQuery = reformatSearch(search);
			products.put(searchQuery, getNewProducts(pageable));
		}
		
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("page", pageable);
		model.addAttribute("products", products);
		return PageAdvice.Shop.SHOP;
	}
	
	
	/** ::: 
	 * <p>Retrieve a list of new products</p>
	 */
	private List<ProductTemplateDTO> getNewProducts(Pageable pageable) {
		DateInterval dateInterval = null;
		List<Category> categories = null;
		final ProductStatus status = ProductStatus.PUBLIC;
		return templateService.getTemplates(categories, status, dateInterval, pageable);
	}
	
	
	private String reformatSearch(String search) {
		return search.replaceAll("[\\s\\-()]", " ");
	}
	
	


	
	
	
}
