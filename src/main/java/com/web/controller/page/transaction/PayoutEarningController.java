package com.web.controller.page.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

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
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.payout.LabelFee;
import com.common.entity.payout.MerchantFee;
import com.common.entity.payout.Payout;
import com.common.exception.MerchantException;
import com.common.type.FeeStatus;
import com.common.type.PayoutStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.merchant.MerchantService;
import com.core.service.payout.MerchantFeeService;
import com.core.service.payout.PayoutService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


/************************************************************************
 * 
 * @author dk
 *
 */
@Controller
public class PayoutEarningController extends AbstractTransactionController
implements IPayoutEarningAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private PayoutService payoutService;
	@Autowired private MerchantFeeService feeService;
	@Autowired private MerchantService merchantService;
	//@Autowired private PayoutTransactionService transactionService;
	//@Autowired private LabelFeeService labelFeeService;
	//@Autowired private CommissionRateService commissionRateService;
	
	
	
/**
 * Display payout the merchant has earned and the services fee that the 
 * customer will be destracted before deposite.
 * 
 * Possible stats:
 * + Earning: 1. Sales
 * + Fee:   1. Label fees
 * 			2. Service fees
 * 
 * @see {@link IPayoutEarningAdvise#showTransactions}
 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.Transaction.PAYOUT_EARNING
				, method=RequestMethod.GET)
	public String showTransactions(Model model
														, @ActiveUserPrincipal Account customer
														, @RequestParam(required=false) Integer _m
														, @RequestParam(required=false) Integer _y) 
	{
		
		
		
		long payoutSize = 0;
		int payoutTotalAmount = 0;
		List<Payout> payouts = new ArrayList<>();
		
		long feeSize = 0;
		int feeTotalAmount = 0;
		List<MerchantFee> fees = new ArrayList<>();
		
		
		DateInterval dateInterval = null;
		int salesFee = 0;
		int estimatedBalance = 0;
		Pageable pageable = new PageSearch(0, 10);
		
		try {
			final Merchant merchant = getAuthorizedMerchant(customer);
			dateInterval = new DateUtil();
			dateInterval.setInterval(new Date(), -90);
			
			PayoutStatus status = null;
			payoutSize = payoutService.countByMerchant(merchant, status, dateInterval);
			if(payoutSize > 0) {
				payouts = payoutService.getByMerchant(merchant, status, dateInterval, pageable);
			}
			payoutTotalAmount = payoutService.getTotalAmountByMerchant(merchant, status, dateInterval);
			
			
			FeeStatus feeStatus = null;
			feeSize = feeService.countByMerchant(merchant, feeStatus , dateInterval);
			if(feeSize > 0) 
				fees = feeService.getByMerchant(merchant, feeStatus, dateInterval, pageable);
			feeTotalAmount = feeService.getTotalFeeByMerchant(merchant, feeStatus, dateInterval);
			
			CommissionRate commissionRate = merchantService.getCommissionRate(merchant);
			salesFee = (int)(payoutTotalAmount * (double)commissionRate.getRateValue() / 10000);
			estimatedBalance = payoutTotalAmount - salesFee - feeTotalAmount;
			
		} catch (MerchantException e) {
			logger.error(e.getMessage());
		}
		
		
		
		model.addAttribute("estimatedBalance", estimatedBalance);
		model.addAttribute("salesFee", salesFee);
		model.addAttribute("payouts", payouts);
		model.addAttribute("payoutSize", payoutSize);
		model.addAttribute("payoutTotalAmount", payoutTotalAmount);
		model.addAttribute("fees", fees);
		model.addAttribute("feeSize", feeSize);
		model.addAttribute("feeTotalAmount", feeTotalAmount);
		model.addAttribute("interval", dateInterval);
		
		
		return PageAdvice.Transaction.PAYOUT_EARNING;
	}

	
}
