package com.web.controller.page.account;

import java.util.Date;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.account.Account;
import com.common.entity.account.PasswordResetToken;
import com.core.service.account.AccountService;
import com.core.service.account.PasswordResetTokenService;
import com.core.service.account.auth.AccountAuthenticationService;
import com.core.service.email.IResetPasswrodEmailService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;


@Controller
public class ForgotPasswordController implements IForgetPasswordAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private PasswordResetTokenService passwordResetTokenService;
	@Autowired private IResetPasswrodEmailService resetPasswrodEmailService;
	@Autowired private AccountAuthenticationService accountAuthenticationService;
	@Autowired private AccountService accountService;
	
	
		
	/**
	 * {@link IForgetPasswordAdvise#showForgotPasswordPage()}
	 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.User.FORGOT_PASSWORD
				, method=RequestMethod.GET)
	public String showForgotPasswordPage() {
		return PageAdvice.User.FORGOT_PASSWORD_FORM;
	}

	
	
	

		
	/**s
	 * {@link IForgetPasswordAdvise#showForgotPassworConfirmPage()}
	 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.User.RESET_PASSWORD
				, method=RequestMethod.GET)
	public String showResetPasswordPage(Model model
									, @RequestParam(name="email", required=false) String _email
									, @RequestParam(name="token") String _tokenId) {
		System.out.println();
		logger.info("Show reset password page");
		try {
			PasswordResetToken token = passwordResetTokenService.getByToken(_tokenId);	
			Assert.notNull(token, "Unable to retrieve the token");
			Assert.isTrue(token.isUseable(), "The token has been used");
			Assert.isTrue(token.getExpiryDate().after(new Date()), "The token has been expired");
			
			Account account = accountService.getByEmail(_email);
			Assert.notNull(account, "Unable to loookup the account by email");
			Assert.isTrue(account.getId().equals(token.getAccount().getId()));
			model.addAttribute("tokenId", _tokenId);
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw new ResourceNotFoundException();
		}
		return PageAdvice.User.RESET_PASSWORD_FORM;
		
	}

	
	

	
/******************************************************************************
 * 
 * {@link IForgetPasswordAdvise#requestToken(String)}
 */
	@Override
	@ResponseBody
	@RequestMapping(value=UriActionRequestMapping.User.REQUEST_PASSWORD_TOKEN
						, method=RequestMethod.POST)
	public JsonResponse requestToken(HttpServletRequest request
									, @RequestParam String _email) {
		System.out.println();
		logger.info("Request to send password token");
		JsonResponse response = new JsonResponse();
		try {
			Account account = accountService.getByEmail(_email);
			Assert.notNull(account, "Invalid email");
			resetPasswrodEmailService.sendPasswordResetEmail(account);
			response.setState(true);
		} catch(MailException e) {
			response.setState(false);
		} catch(IllegalArgumentException e) {
			response.setState(false);
		}

		System.out.println("----");
		System.out.println("- state ---" + response.isState());
		System.out.println("----");
	
		return response;
	}



	
/******************************************************************************
 * 
 * @throws AccountException 
 * @see {@link IForgetPasswordAdvise#resetPassword(String, String, String)}
 * @see {@link AccountAuthenticationService#changePassword(Account, String)}
 * 
 */
	@Override
	@RequestMapping(value=UriActionRequestMapping.User.REQUEST_RESET_PASSWORD
					, method=RequestMethod.POST)	
	@ResponseBody
	public JsonResponse resetPassword(
			@RequestParam(name="_t") String _tokenId
			, @RequestParam(name="_np") String _newPwd
			, @RequestParam(name="_cp") String _confirmPwd) throws AccountException 
	{
		JsonResponse jsonResponse = new JsonResponse();
		PasswordResetToken token = passwordResetTokenService.getByToken(_tokenId);
		if(token != null) {
			Account customer = accountService.findById(token.getAccount().getId());
			Assert.notNull(customer, "Unable to ");
			if(_newPwd.equals(_confirmPwd)){
				accountAuthenticationService.changePassword(customer.getId(), _newPwd);
				passwordResetTokenService.markAsHasUsedByAccount(customer);
				jsonResponse.setState(true);
			} else {
				jsonResponse.setState(false);
			}
		} else {
			logger.error("Unable to find reset token");
		}
		
		return jsonResponse;
	}
}
