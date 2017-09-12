package com.web.controller.page.setting;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface IAccountSettingAdvise {
	
	String displayGeneralAccountSetting(Model model, Account customer); 
	
	JsonResponse updateGeneralAccount(Account customer
			, String _username, String _email);
	
	String updatePhoto(Account customer, MultipartFile _image);
}
