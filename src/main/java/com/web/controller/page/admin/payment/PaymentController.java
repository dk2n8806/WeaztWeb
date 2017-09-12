package com.web.controller.page.admin.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.payment.Payment;
import com.common.exception.AccountCredentialException;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.payment.PaymentService;
import com.core.service.process.payment.IChargePaymentService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.controller.page.admin.AbstractAdminBase;

@Controller("adminPaymentController")
public class PaymentController  extends AbstractAdminBase {

	@Autowired private PaymentService paymentService;
	@Autowired private IChargePaymentService chargePaymentService;
	
	@RequestMapping(value=UriAdminRequestMapping.Payment.PAYMENT
								, method=RequestMethod.GET)
	public String showPage(Model model
											, @ActiveUserPrincipal Account account
											, @RequestParam(defaultValue="0") int _p
											, @RequestParam(defaultValue="10") int _m
											, @RequestParam(defaultValue="all") String _s
								) throws AccountCredentialException 
	{
	
		isAdmin(account);
		Map<String, Object> params = new HashMap<>();
		long pending = paymentService.countPayments(PaymentStatus.PENDING, null, null);
		long completed = paymentService.countPayments(PaymentStatus.COMPLETED, null, null);
		long voided = paymentService.countPayments(PaymentStatus.VOIDED, null, null);
		params.put("pending", pending);
		params.put("completed", completed);
		params.put("voided", voided);
		params.put("all", pending + completed + voided);
		
		
		PaymentStatus status = PaymentStatus.lookup(_s);
		DateInterval dateInterval = null;
		Pageable pageable = new PageSearch(0, 10);
		Boolean isRequested = null;
		List<Payment> payments = paymentService.getPayments(status, isRequested, dateInterval, pageable);
		
		model.addAttribute("payments", payments);
		model.addAttribute("params", params);
		model.addAttribute("status", _s);
		return PageAdvice.Admin.Payment.PAYMENT_PAGE;
	}
	
	

	@RequestMapping(value=UriAdminRequestMapping.Payment.CHARGE_PAYMENT
								, method=RequestMethod.POST)
	@ResponseBody
	public void charge(@ActiveUserPrincipal Account account
			) throws AccountCredentialException 
	{
		isAdmin(account);
		DateInterval dateInterval = null;
		Pageable pageable = null;
		boolean isRequested = true;
		List<Payment> payments = paymentService.getPayments(
									PaymentStatus.PENDING, isRequested, dateInterval, pageable);
		for(Payment payment : payments) {
			try {
				chargePaymentService.charge(payment);
			} catch (PaymentException e) {
				e.printStackTrace();
			}
		}
	}

}
