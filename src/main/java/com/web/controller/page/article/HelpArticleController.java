package com.web.controller.page.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.advice.UriArticleRequestMapping;

@Controller
public class HelpArticleController {

	
	@RequestMapping(value=UriArticleRequestMapping.HELP, method=RequestMethod.GET)
	public String showHelpPage() {
		return "article/help/help";
	}
	
	
	@RequestMapping(value=UriArticleRequestMapping.HELP_ARTICLE, method=RequestMethod.GET)
	public String showArticlePage(@PathVariable(value="name") String name) {
		return "article/help/" + name;
	}
}
