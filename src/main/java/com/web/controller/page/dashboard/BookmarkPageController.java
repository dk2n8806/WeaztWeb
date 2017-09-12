package com.web.controller.page.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.Bookmark;
import com.common.util.date.DateInterval;
import com.core.service.product.ProductService;
import com.core.service.product.BookmarkService;
import com.core.service.product.dto.BookmarkTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.response.JsonResponse;


@Controller
public class BookmarkPageController {
	

	@Autowired private BookmarkService wishListService;
	@Autowired private ProductService productService;
	@Autowired private BookmarkTemplateDTOService templateDTOService;
	
	
	
	/** :::
	 * ::: */
	@RequestMapping(value="/user/bookmark"
								, method=RequestMethod.GET)
	public String showPage(Model model, @ActiveUserPrincipal Account account) 
	{
		DateInterval dateInterval = null;
		Pageable pageable = null;
		List<BookmarkProductTemplateDTO> templates = 
				templateDTOService.getByAccount(account, dateInterval, pageable);
		model.addAttribute("templates", templates);
		return PageAdvice.User.BOOKMARK_PAGE;
	}


	
	/** :::
	 * <p>Bookmark a product to a client's list</p>
	 * 
	 * ::: */
	@RequestMapping(value="/user/bookmark/add"
								, method=RequestMethod.GET)
	@ResponseBody
	public JsonResponse addToList(@ActiveUserPrincipal Account account
									, @RequestParam(defaultValue="0") Long productId) 
	{
		synchronized (this) {
			System.out.println("here");
			JsonResponse json = new JsonResponse();
			try {
				Product product = productService.findById(productId);
				Assert.notNull(product,"product can't be null");
				Bookmark list = wishListService.save(account, product);
				Assert.notNull(list, "Wishlist can't be null");
				Assert.notNull(list.getId(), "List id can't be null");
				
				json.setState(true);
			} catch(IllegalArgumentException e) { 
				e.printStackTrace();
				json.setState(false);
			}
			return json;
		}
	}

	

	
	/** :::
	 * ::: */
	@RequestMapping(value="/user/bookmark/remove"
								, method=RequestMethod.GET)
	@ResponseBody
	public JsonResponse removeFromList(@ActiveUserPrincipal Account account
																, @RequestParam(defaultValue="0") Long productId) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			Bookmark wishList = wishListService.getByAccount(productId, account);
			
			if(wishList != null ){
				wishListService.remove(wishList);
			}
			return json;
		}
	}
	
}
