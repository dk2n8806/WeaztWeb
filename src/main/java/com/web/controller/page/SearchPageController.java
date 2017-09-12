package com.web.controller.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.dto.product.ProductTemplateDTO;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.core.service.product.dto.SearchProductTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class SearchPageController {

	
	@Autowired private SearchProductTemplateDTOService searchProductService;

	
		
	/** :::
	 * <p>Display search result when the customer input a string into the search bar</p>
	 * 
	 * {@link ISearch#displaySearch}
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.Shop.SEARCH
								, method=RequestMethod.GET)
	public String displaySearch(Model model
								, @RequestParam(defaultValue="") String ref
								, @RequestParam(defaultValue="0") int pageNumber
								, @RequestParam(defaultValue="30") int pageSize) 
	{
		if(!ref.equals("")) {
			List<ProductTemplateDTO> templates = new ArrayList<>();
			ProductStatus status = ProductStatus.PUBLIC;
			Pageable pageable = new PageSearch(pageNumber, pageSize);
			long count = searchProductService.countByTitle(ref, status );
			if(count > 0) {
				templates = searchProductService.getByTitle(ref, status, pageable);
			}
			model.addAttribute("searchString", ref);
			model.addAttribute("page", pageable);
			model.addAttribute("searchCount", count);
			model.addAttribute("templates", templates);
		}
		return PageAdvice.Shop.SEARCH;
	}
	
	
	
	
	
	
}
