package com.web.controller.page.checkout;

import org.springframework.ui.Model;

import com.common.entity.account.Account;

/*****************************************************************************
 * 
 * @author dk
 *
 */
public interface ICheckoutConfirmationAdvise {

	/** {@link CheckoutConfirmPageController#showConfirmationPage(Model, Account, Long)} */
	String showConfirmationPage(Model model, Account customer, Long _sid);
	
	
}
