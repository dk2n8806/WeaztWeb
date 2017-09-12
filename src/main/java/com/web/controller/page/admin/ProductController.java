package com.web.controller.page.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.entity.product.Product;
import com.common.exception.AccountCredentialException;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.core.service.product.ProductService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;

@Controller("adminProductController")
public class ProductController extends AbstractAdminBase {

	private static Logger logger = LogManager.getLogger();
	

	@Autowired private ProductService productService;
	
	
	
	@RequestMapping(value=UriAdminRequestMapping.PRODUCT.DISPLAY_PRODUCTS
								, method=RequestMethod.GET)
	public String showPage(Model model
								, @ActiveUserPrincipal Account customer
								, @RequestParam(name="_ref", defaultValue="all") String _status
								, @RequestParam(defaultValue="10") 	int _m
								, @RequestParam(defaultValue="0")		 int _p
								) 
										throws AccountCredentialException 
	{
		System.out.println();
		logger.info("Display account_templates");
		
		isAdmin(customer);
		Category category = null;
		
		long activeCount = productService.countProducts(category, ProductStatus.PUBLIC, null);
		long deactiveCount = productService.countProducts(category, ProductStatus.PRIVATE, null);
		long deletedCount = productService.countProducts(category, ProductStatus.DELETED, null);
		long allCount = productService.getRowCount();
		
		Map<String, Long> counts = new HashMap<>();
		counts.put("public", activeCount);
		counts.put("all", allCount);
		counts.put("private", deactiveCount);
		counts.put("deleted", deletedCount);
		
		Pageable pageable = new PageSearch(_p, _m);
		List<Product> list = productService.getProducts(category, ProductStatus.lookup(_status), null, pageable);
		

		model.addAttribute("counts", counts);
		model.addAttribute("products", list);
		model.addAttribute("status", _status);
		return PageAdvice.Admin.DISPLAY_PRODUCTS;
	}
	
	

	
}