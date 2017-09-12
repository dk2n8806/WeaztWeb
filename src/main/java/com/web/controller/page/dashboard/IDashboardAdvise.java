package com.web.controller.page.dashboard;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;

public interface IDashboardAdvise {
	
	/** {@link DashboardPageController#showPage(Model, Account)} 
	 * @throws MerchantException */
	String showPage(Model model, Account account, String location) throws MerchantException;
}
