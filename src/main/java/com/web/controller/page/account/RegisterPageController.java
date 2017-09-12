package com.web.controller.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.account.Account;
import com.core.service.account.auth.AccountAuthenticationService;
import com.core.service.email.IWelcomeEmailService;
import com.mysema.commons.lang.Assert;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class RegisterPageController {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private AccountAuthenticationService authenService;
	@Autowired private IWelcomeEmailService welcomeEmailService;
	
	/** :::
	 * <p>Display REGISTER page</p>
	 * */
	@RequestMapping(value=UriPageRequestMapping.User.REGISTER
														, method=RequestMethod.GET)
	public String showPage(Model model) {
		System.out.println();
		logger.info("Display register form");
		Account account = new Account();
		model.addAttribute("newUser", account);
		return PageAdvice.User.REGISTER_FORM;
	}
	
	
	
	
	/** :::
	 * <p>Handle REGISTER_FORM</p>
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.User.REGISTER
											, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse processRegisterForm(Model model
															, @ModelAttribute("newUser") Account account
															, @RequestParam("password") String password
			) {
		System.out.println();
		logger.info("Request to register a new account");
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				Account entity = authenService.save(account, password);
				Assert.notNull(entity, "Unable to create new a new account entity");
			
				welcomeEmailService.sendWelcomeEmail(entity);
				jsonResponse.setState(true);
			} catch(DataIntegrityViolationException e) {
				jsonResponse.setState(false);
				logger.error("__ Duplication unique restriction");
			} catch(Exception e) {
				jsonResponse.setState(false);
				logger.error("___ Nasty error");
				e.printStackTrace();
			}
			return jsonResponse;
		}
	}
	
	
}
