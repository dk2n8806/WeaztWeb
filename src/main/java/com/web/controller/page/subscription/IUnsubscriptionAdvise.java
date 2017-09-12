package com.web.controller.page.subscription;

import org.springframework.ui.Model;

import com.common.entity.account.Account;

public interface IUnsubscriptionAdvise {

	/** {@link UnsubscriptionController#showPage(Model, Account, String, String, int)} */
	String showPage(Model model, Account customer, String status, String role, int page);
}
