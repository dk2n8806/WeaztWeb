package com.web.controller.page.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.web.advice.PageAdvice;
import com.web.advice.UriArticleRequestMapping;

@Controller
public class PartnerIndexPageController {

	
//	@RequestMapping(value=UriArticleRequestMapping.PARTNER
//					, method=RequestMethod.GET)
//	public String showPartnerIndextPage(@ActiveUserPrincipal Account customer) {
//		return PageAdvice.Article.PARTNER_INDEX_PAGE;
//	}
}
