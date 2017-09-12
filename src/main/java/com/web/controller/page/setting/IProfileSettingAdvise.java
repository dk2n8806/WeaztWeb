package com.web.controller.page.setting;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface IProfileSettingAdvise {

	/** Display profile setting page of a customer 
	 * {@link ProfileSettingController#showPage} */
	String showPage(Model model, Account customer);

	
	
	/** Update profile of customer
	 * {@link ProfileSettingController#updateProfile}*/
	JsonResponse updateProfile(Account customer
			, HttpServletResponse response
			, String _name
			, String _gender, String _phone
			, String _street, String _city
			, String _state, String _zipcode
			, String _date, String _month, String _year);
}
