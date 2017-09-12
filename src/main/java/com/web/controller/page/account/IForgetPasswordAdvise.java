package com.web.controller.page.account;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.web.response.JsonResponse;

public interface IForgetPasswordAdvise {

	/** {@link ForgotPasswordController#showForgotPasswordPage()} */
	String showForgotPasswordPage();
	
	/** {@link ForgotPasswordController#showResetPasswordPage(Model, String, String)} */
	String showResetPasswordPage(Model model, String email, String tokenId);
	
	
	/** {@link ForgotPasswordController#requestToken(HttpServletRequest, String)} */
	JsonResponse requestToken(HttpServletRequest request, String email);
	
	JsonResponse resetPassword(String _token, String _newPwd, String _confirmPwd) throws AccountException;
	
}
