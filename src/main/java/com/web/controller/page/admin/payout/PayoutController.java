package com.web.controller.page.admin.payout;

import java.util.ArrayList;
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

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.payout.Payout;
import com.common.exception.AccountCredentialException;
import com.common.type.PayoutStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.payout.PayoutService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.controller.page.admin.AbstractAdminBase;


@Controller("adminPayoutController")
public class PayoutController  extends AbstractAdminBase {

	@Autowired private PayoutService payoutService;
	
	
	@RequestMapping(value=UriAdminRequestMapping.Payout.PAYOUTS
								, method=RequestMethod.GET)
	public String showPage(Model model
											, @ActiveUserPrincipal Account account
											, @RequestParam(defaultValue="0") int _p
											, @RequestParam(defaultValue="10") int _m
											, @RequestParam(defaultValue="all") String _s
								) throws AccountCredentialException 
	{
		isAdmin(account);
		List<Payout> payouts = new ArrayList<>();
		PayoutStatus status = PayoutStatus.lookup(_s);
		DateInterval dateInterval = null;
		Pageable pageable = new PageSearch(_p, _m);
		payouts = payoutService.getPayouts(status, dateInterval, pageable );
		
		Map<String, Object> params = new HashMap<>();
		long pending = payoutService.countPayouts(PayoutStatus.PENDING, null);
		long depositied = payoutService.countPayouts(PayoutStatus.COMPLETED, null);
		params.put("pending", pending);
		params.put("deposited", depositied);
		params.put("all", pending + depositied);
		
		
		model.addAttribute("params", params);
		model.addAttribute("payouts", payouts);
		model.addAttribute("status", status);

		return PageAdvice.Admin.Payout.PAYOUT_PAGE;
	}
	
}