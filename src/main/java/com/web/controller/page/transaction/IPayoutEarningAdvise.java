package com.web.controller.page.transaction;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeTransfer;

public interface IPayoutEarningAdvise {

	/** Display a list of payout_transaction {@link StripeTransfer}
	 * by the merchant 
	 * 
	 * {@link PayoutEarningController#showTransactions} */
	String showTransactions(Model model, Account customer, Integer toMonth, Integer year);
	
}
