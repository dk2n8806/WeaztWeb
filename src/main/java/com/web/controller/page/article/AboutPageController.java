package com.web.controller.page.article;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.advice.PageAdvice;
import com.web.advice.UriArticleRequestMapping;

@Controller
public class AboutPageController {

	private static final Logger logger = LogManager.getLogger();

	
	
	@RequestMapping(value=UriArticleRequestMapping.ABOUT
					, method=RequestMethod.GET)
	public String showAboutPage() {
		System.out.println();
		logger.info("Show about page");
		return PageAdvice.Article.ABOUT_PAGE;
	}
	
	
	
	
	@RequestMapping(value=UriArticleRequestMapping.TOS
					, method=RequestMethod.GET)
	public String showTOSPage() {
		System.out.println();
		logger.info("Show tos_term page");
		return PageAdvice.Article.TOS_TERM_PAGE;
	}
	

	
	
	@RequestMapping(value=UriArticleRequestMapping.PRIVACY
					, method=RequestMethod.GET)
	public String showPrivacyPage() {
		System.out.println();
		logger.info("Show privacy_term page");
		return PageAdvice.Article.PRIVACY_TERM_PAGE;
	}
	
	
	
	
}
