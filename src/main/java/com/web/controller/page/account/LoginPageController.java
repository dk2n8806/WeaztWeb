package com.web.controller.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.entity.account.Account;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


@Controller
public class LoginPageController {
	
	private static final Logger log = LogManager.getLogger();
	
	
	@RequestMapping(value=UriPageRequestMapping.User.LOGIN
					, method=RequestMethod.GET)
	public String showPage(Model model) {

		System.out.println();
		log.info("Display login form");
		model.addAttribute("newUser", new Account());
		return PageAdvice.User.LOGIN_FORM;
	}
}
