package com.web.controller.page.transaction;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.refund.Refund;
import com.common.entity.refund.RefundStatus;
import com.common.util.date.DateInterval;
import com.core.service.refund.RefundService;
import com.web.advice.UriPageRequestMapping;


@Controller
public class RefundPageController implements IPaymentRefundAdvise {

	private static final Logger logger = LogManager.getLogger();

	@Autowired private RefundService refundService;
	
	@RequestMapping(value=UriPageRequestMapping.Transaction.PAYMENT_REFUND
					, method=RequestMethod.GET)
	public String showPage(Model model, @ActiveUserPrincipal Account account) {
		logger.info("Display payment refund");
		
		RefundStatus status = null;
		DateInterval dateInterval = null;
		Pageable pageable = null;
		List<Refund> refunds = refundService.getByAccount(account, status, dateInterval, pageable);
		
		model.addAttribute("refunds", refunds);
		return "";
	}
}
