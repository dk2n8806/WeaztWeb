package com.web.controller.page.setting;


import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

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
import com.common.entity.account.VerificationToken;
import com.core.service.account.VerificationTokenService;
import com.core.service.account.auth.AccountAuthenticationService;
import com.core.service.email.IEmailVerificationService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class AccountVerificationController 
implements IAccountVerificationAdvise 
{
	
	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private VerificationTokenService verifiedService;
	@Autowired private AccountAuthenticationService accountAuthenService;
	@Autowired private IEmailVerificationService emailService;
	
	
	
	/** :::
	 * <p>Display verification page</p>
	 * 
	 * {@link IAccountVerificationAdvise#showAccountVerificationPage(Model, Account)}
	 * ::: */ 
	@Override
	@RequestMapping(value=UriPageRequestMapping.Setting.VERIFICATION_SETTING
					, method=RequestMethod.GET)
	public String showAccountVerificationPage(Model model
						, @ActiveUserPrincipal Account customer) 
	{
		return PageAdvice.Setting.VERIFICATION_SETTING;
	}

	
	
	
	/** :::
	 * <p>Request to send a new verification code</p>
	 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.User.VERIFIED_ACCOUNT
				, method=RequestMethod.GET)
	public String processVerificationCode(Model model
					, @ActiveUserPrincipal Account customer
					, @RequestParam(name="id") Long id
					, @RequestParam(name="token") String token) 
	{
		System.out.println();
		logger.info("Verify the account by the account");
		boolean success = false;
		synchronized (this) {
			if(customer.isHasVerified()) {
				success = true;
			}
			VerificationToken vToken = verifiedService.getByToken(token);
			if(vToken != null 	&& vToken.getVersion() < 2
										&& vToken.getToken().equals(token)
										&& vToken.isUseable()
										&& vToken.getAccount().getId().equals(id)) 
			{
				try {
					accountAuthenService.verifiedEmailAccount(id);
					verifiedService.markAsUsed(vToken);
					if(customer != null) {
						customer.setHasVerified(true);
					}
					success = true;
				} catch (AccountException e) {
					logger.info("Unable to find the account");
					e.printStackTrace();
				}
			}
 		}
		model.addAttribute("success", success);
		return PageAdvice.GlobalReponse.VERIFICATION_RESPONSE;
	}

	
	
	
	/** :::
	 * <p>Handle to resend an email verification to the account</p>
	 */
	@Override
	@RequestMapping(value=UriActionRequestMapping.Setting.VERIFIED_ACCOUNT_REQUEST
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse requestNewVerificationToken(
							@ActiveUserPrincipal Account customer
							, HttpServletRequest request) {
		System.out.println();
		logger.info("The account request to send a new verification link");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			emailService.sendVerfiicationEmail(customer);
			jsonResponse.setState(true);
		} catch(Exception e) {
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
	
	
	
	

}
