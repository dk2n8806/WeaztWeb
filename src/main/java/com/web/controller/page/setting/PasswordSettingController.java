package com.web.controller.page.setting;

import javax.security.auth.login.AccountException;

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
import com.core.service.account.auth.AccountAuthenticationService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class PasswordSettingController implements IPasswordAdvise{

	private static final Logger log = LogManager.getLogger();
	
	@Autowired private AccountAuthenticationService accountAuthenticationService;
		
	/** :::s
	 * <p>Display password page</p>
	 * 
	 * {@link IPasswordAdvise#showPasswordSettingPage(Model, Account)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Setting.PASSWORD_SETTING
				, method=RequestMethod.GET)
	public String showPasswordSettingPage(Model model
														, @ActiveUserPrincipal Account customer) 
	{
		System.out.println();
		log.info("Customer request to show update-password form");
		return PageAdvice.Setting.PASSWORD_SETTING;
	}
		
		
		
		
	/** :::
	 * <p>Handle client request to update password</p>
	 * 
	 * {@link IPasswordAdvise#updatePassword(Account, String, String, String)}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_PASSWORD
				, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse	updatePassword(@ActiveUserPrincipal Account customer
						, @RequestParam(defaultValue="") String _old
						, @RequestParam(defaultValue="") String _new
						, @RequestParam(defaultValue="") String _con) {
		JsonResponse jsonResponse = new JsonResponse();
		log.info("Customer request update password: " + customer.getHashedPassword());
		try {
			if(_new.equals(_con)){
				if(accountAuthenticationService.verifyPassword(customer.getId(), _old)) {
					accountAuthenticationService.changePassword(customer.getId(), _new);
					jsonResponse.setState(true);
				} else {
					log.info("Password doesn't match with the file");
					jsonResponse.setState(false);
				}		
			} else {
				log.error("Confirmed password doesn't match");
				jsonResponse.setState(false);
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
			jsonResponse.setState(false);
		} catch (AccountException e) {
			e.printStackTrace();
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}

}
