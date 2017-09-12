package com.web.controller.page.setting;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;

public interface IMerchantSettingAdvise {

	/** {@link MerchantSettingController#showSettingPage} */
	String showSettingPage(Model model, Account customer) throws MerchantException;
	
	/** {@link MerchantSettingController#updateMerchantInfo} */
	JsonResponse updateContact(Account customer, String _company
							, String _website, String _phone
							, String _street, String _city
							, String _state, String _zipcode);
	
	/** {@link MerchantSettingController#updateTax} */
	//JsonResponse updateTax(Account customer, Long _rate);
}
