package com.web.controller.page.setting;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface IPasswordAdvise {

	/** {@link PasswordSettingController#showPasswordSettingPage(Model, Account)} */
	String showPasswordSettingPage(Model model, Account account);
	
	/** {@link PasswordSettingController#updatePassword(Account, String, String, String)} */
	JsonResponse updatePassword(Account account, String a, String b, String c);
	
}
