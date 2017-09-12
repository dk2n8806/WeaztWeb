package com.web.controller.page.merchant;


import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;

public interface IRegisterMerchantController {

	/** {@link RegisterMerchantContoller#showRegisterForm} */
	String showRegisterForm(Model model, Account customer) throws MerchantException;
	
	/** {@link RegisterMerchantContoller#createNewMerchant} */
	JsonResponse createNewMerchant(Account customer, String _bname
															, String _baddress, String _bcity, String _bstate, String _bzipcode
															, String _bphone, String _bwebsite
															);
}
