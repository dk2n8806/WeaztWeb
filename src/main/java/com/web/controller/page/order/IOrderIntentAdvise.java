package com.web.controller.page.order;

import org.springframework.ui.Model;
import com.common.entity.account.Account;

public interface IOrderIntentAdvise {
	
	
	/** {@link OrderIntentController#showGroupOrderOnProducts(Model, Account, String)} */
	String showGroupOrderOnProducts(Model model, Account customer, String _date);
	
}
