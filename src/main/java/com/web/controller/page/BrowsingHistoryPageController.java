package com.web.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.RecentViewProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.core.service.subscriber.RecentVisitedTemplateDTOService;
import com.web.advice.PageAdvice;

@Controller
public class BrowsingHistoryPageController 
{

	@Autowired private RecentVisitedTemplateDTOService templateService;

	@RequestMapping(value="/user/recent-views", method=RequestMethod.GET)
	public String showPage(Model model
										,@ActiveUserPrincipal Account account
										, @RequestParam(name="p", defaultValue="0") int pageNumber
										, @RequestParam(name="s", defaultValue="30") int pageSize) 
	{
		
		Map<String, List<RecentViewProductTemplateDTO>> products = 
				new HashMap<String, List<RecentViewProductTemplateDTO>>();
		String searchQuery = "Recent views";
		final Pageable pageable = new PageSearch(pageNumber, pageSize);
		ProductStatus status = ProductStatus.PUBLIC;
		products.put(searchQuery, templateService.getByAccount(account, status , pageable));
		
		
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("page", pageable);
		model.addAttribute("products", products);
		return PageAdvice.Shop.SHOP;
	}
}
