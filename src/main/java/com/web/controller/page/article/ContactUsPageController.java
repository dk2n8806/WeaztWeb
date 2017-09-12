package com.web.controller.page.article;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.support.ContactSupportMessage;
import com.common.entity.support.SupportCategory;
import com.core.service.support.ContactSupportMessageService;
import com.web.advice.PageAdvice;
import com.web.advice.UriArticleRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class ContactUsPageController {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ContactSupportMessageService supportService;
	
	
	@RequestMapping(value=UriArticleRequestMapping.CONTACT
					, method=RequestMethod.GET)
	public String showPage(Model model) {
		System.out.println();
		logger.info("Show contact_us page");
		model.addAttribute("categories", SupportCategory.getCategories());
		return PageAdvice.Article.CONTACT_US_PAGE;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value=UriArticleRequestMapping.CONTACT_REQUEST
					, method=RequestMethod.POST)
	public JsonResponse sendMessage(
			 				@ActiveUserPrincipal Account customer
							,@RequestParam(name="_s") String subject
							,@RequestParam(name="_sc") Integer category
							,@RequestParam(name="_m") String message) {
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			ContactSupportMessage support = supportService.save(customer, subject, SupportCategory.lookup(category), message);
			if(support == null) {
				jsonResponse.setState(false);
			} else {
				jsonResponse.setState(true);
			}
			return jsonResponse;
		}
	}
}
