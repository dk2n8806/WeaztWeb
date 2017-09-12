package com.web.controller.page.transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.payment.SubscriptionPayment;
import com.common.type.PaymentStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.payment.SubscriptionPaymentService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class PaymentTransactionController extends AbstractTransactionController
implements IPaymentTransactionHistoryAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private SubscriptionPaymentService paymentService;
	
/******************************************************************************
 * {@link IPaymentTransactionHistoryAdvise#showTransactions(Model, Account, String, String, String)}
 * 
 * Display payment transaction history by a account
 * 
 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.Transaction.PAYMENT_TRANSACTION
				, method=RequestMethod.GET)
	public String showTransactions(Model model
													, @ActiveUserPrincipal Account account
													, @RequestParam(required=false) Integer _m
													, @RequestParam(required=false) Integer _y) 
	{
		System.out.println();
		logger.info("Display payment transaction in the history");
		List<SubscriptionPayment> payments = new ArrayList<SubscriptionPayment>();
		DateInterval dateInterval = null;
		Pageable pageable = null;
		
		dateInterval = getInterval(_m, _y);
		PaymentStatus status = null;
		long size = paymentService.countByAccount(account, status , dateInterval);
		
		if(size > 0) {
			pageable = new PageSearch(0, 10);
			payments = paymentService.getByAccount(account, status, dateInterval, pageable);
		}
		
		model.addAttribute("payments", payments);
		model.addAttribute("size", size);
		model.addAttribute("dateInterval", dateInterval);
		
		return PageAdvice.Transaction.PAYMENT_TRANSACTION;
	}

}
