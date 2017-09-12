package com.web.controller.page.transaction;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCharge;

public interface IPaymentTransactionHistoryAdvise {

	
	/** Display a list of payment_transaction {@link StripeCharge} 
	 * by the customer
	 *  
	 * {@link PaymentTransactionController#showTransactions(Model, Account, Integer, Integer, Integer)} */
	String showTransactions(Model model, Account customer
						, Integer month, Integer year);
	
}
