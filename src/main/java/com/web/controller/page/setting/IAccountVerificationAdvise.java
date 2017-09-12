package com.web.controller.page.setting;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface IAccountVerificationAdvise {

	/** {@link AccountVerificationController#showAccountVerificationPage(Model, Account)} */
	String showAccountVerificationPage(Model model, Account customer);
	
	
	/** {@link AccountVerificationController#processVerificationCode(Model, Account, Long, String)} */
	String processVerificationCode(Model model, Account customer, Long id, String token);
	
	
	/** {@link AccountVerificationController#requestNewVerificationToken(Account)} */
	JsonResponse requestNewVerificationToken(Account customer, HttpServletRequest request);
	
}
