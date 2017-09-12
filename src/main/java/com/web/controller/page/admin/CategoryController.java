package com.web.controller.page.admin;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.exception.AccountCredentialException;
import com.core.service.category.CategoryService;
import com.core.service.category.GroupCategoryService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.response.JsonResponse;

@Controller("adminCategoryController")
public class CategoryController extends AbstractAdminBase {

	@Autowired private CategoryService categoryService;
	@Autowired private GroupCategoryService groupCategoryService;
	

	
	
	@RequestMapping(value=UriAdminRequestMapping.Category.DISPLAY_CATEGORIES
								, method=RequestMethod.GET)
	public String showPage(Model model
										, @ActiveUserPrincipal Account account
										, @RequestParam(name="gid", defaultValue="0") Long groupId
										, @RequestParam(name="ref", required=false) Boolean isActive) 
									throws AccountCredentialException 
	{
		isAdmin(account);
		GroupCategory group = groupCategoryService.findById(groupId);
//		List<Category> categories = new ArrayList<>();
//		if(groupCategory != null) 
//			categories = mapperService.getByGroup(groupCategory, isActive);
//		else categories = categoryService.getList();
//		
		
		List<Category> categories = new ArrayList<>();
		if(group != null) categories = categoryService.getByGroup(group, isActive);
		else categories = categoryService.getCategories(isActive);
		
		model.addAttribute("gid", groupId);
		model.addAttribute("ref", isActive);
		model.addAttribute("groups", groupCategoryService.getList());
		model.addAttribute("categories", categories);
		return PageAdvice.Admin.DISPLAY_CATEGORIES;
	}
	
	

	@RequestMapping(value=UriAdminRequestMapping.Category.CREATE_CATEGORY
								, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse createCategory(@ActiveUserPrincipal Account account
												, @RequestParam String name
												, @RequestParam String groupName) {
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				GroupCategory group = groupCategoryService.getByName(groupName);
				Assert.notNull(group, "Unable to retrieve group");
				Category category = categoryService.save(name, group);
				Assert.notNull(category, "unable to create category");
				jsonResponse.setState(true);
			} catch(IllegalArgumentException e) {
				jsonResponse.setState(false);
			}
			return jsonResponse;
		}
	}
	
	
	
	
	@RequestMapping(value=UriAdminRequestMapping.Category.TOGGLE_STATUS
								, method=RequestMethod.GET)
	public JsonResponse toggleCategoryActivation(@ActiveUserPrincipal Account account
																					, @RequestParam(name="cid") Long catId) {
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				categoryService.toggleStatus(catId);
				jsonResponse.setState(true);
			} catch(IllegalArgumentException e) {
				jsonResponse.setState(false);
			}
	
			return jsonResponse;
		}
	}

}
