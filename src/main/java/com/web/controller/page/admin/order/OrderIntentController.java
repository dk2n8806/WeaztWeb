package com.web.controller.page.admin.order;

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
import com.common.entity.order.OrderIntent;
import com.common.exception.AccountCredentialException;
import com.common.type.OrderIntentStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.OrderIntentService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.controller.page.admin.AbstractAdminBase;

@Controller("adminOrderIntentController")
public class OrderIntentController extends AbstractAdminBase {

	@Autowired private OrderIntentService orderIntentService;
	
	@RequestMapping(value=UriAdminRequestMapping.Order.DISPLAY_ORDER_INTENTS
							, method=RequestMethod.GET)
	public String showPage(Model model
									, @ActiveUserPrincipal Account account
									, @RequestParam(defaultValue="0") int _p
									, @RequestParam(defaultValue="10") int _m
									, @RequestParam(defaultValue="all") String _s
			) throws AccountCredentialException 
	{
	
		isAdmin(account);
		
		OrderIntentStatus status = OrderIntentStatus.lookup(_s);
		DateInterval dateInterval = null;
		Pageable pageable = new PageSearch(_p, _m);
		List<OrderIntent> orderIntents = orderIntentService.getOrderIntents(status, dateInterval, pageable);
		

		Map<String, Object> params = new HashMap<>();
		long completed = orderIntentService.countOrderIntents(OrderIntentStatus.COMPLETED, dateInterval);
		long canceled = orderIntentService.countOrderIntents(OrderIntentStatus.CANCELED, dateInterval);
		long requesting = orderIntentService.countOrderIntents(OrderIntentStatus.REQUESTING, dateInterval);
		
		params.put("all", completed + canceled + requesting);
		params.put("completed", completed);
		params.put("canceled", canceled);
		params.put("requesting", requesting);
		
		
		model.addAttribute("params", params);
		model.addAttribute("orderIntents", orderIntents);
		model.addAttribute("status", status);
		return PageAdvice.Admin.Order.ORER_INTENT_PAGE;
	}
}
