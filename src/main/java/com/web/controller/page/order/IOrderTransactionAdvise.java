package com.web.controller.page.order;

import org.springframework.ui.Model;

import com.common.entity.account.Account;

public interface IOrderTransactionAdvise {

	/** {@link OrderTransactionController#showOrderTransactions(Model, Account, String, Long)} */
	String showOrderTransactions(Model model, Account customer, String ref, Integer _page);
}
