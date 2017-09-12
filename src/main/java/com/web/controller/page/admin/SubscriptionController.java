package com.web.controller.page.admin;

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
import com.common.entity.subscription.Subscription;
import com.common.exception.AccountCredentialException;
import com.common.type.SubscriptionStatus;
import com.common.util.PageSearch;
import com.core.service.subscription.SubscriptionService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;

@Controller("adminSubscriptionController")
public class SubscriptionController extends AbstractAdminBase {


	
	@Autowired private SubscriptionService subscriptionService;
	
	
		
	@RequestMapping(value=UriAdminRequestMapping.Subscription.DISPLAY_SUBSCRIPTIONS, method=RequestMethod.GET)
	public String showPage(Model model
								, @ActiveUserPrincipal Account customer
								, @RequestParam(name="_ref", defaultValue="all") String _status
								, @RequestParam(defaultValue="10") 	int _m
								, @RequestParam(defaultValue="0")		 int _p
								) 
										throws AccountCredentialException 
	{
		isAdmin(customer);
		
		long subscribed = subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBED, null);
		long subscribing = subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBING, null);
		long unsubscribed = subscriptionService.countSubscriptions(SubscriptionStatus.UNSUBSCRIBED, null);
		long size = subscriptionService.getRowCount();
		
		Map<String, Long> counts = new HashMap<String, Long>();
		counts.put("all", size);
		counts.put("subscribed", subscribed);
		counts.put("subscribing", subscribing);
		counts.put("unsubscribed", unsubscribed);
		
		Pageable pageable = new PageSearch(_p, _m);
		List<Subscription> subscriptions = subscriptionService.getSubscriptions(SubscriptionStatus.lookup(_status), null, pageable);
		

		model.addAttribute("counts", counts);
		model.addAttribute("subscriptions", subscriptions);
		model.addAttribute("status", _status);
		return PageAdvice.Admin.Display_SUBSCRIPTIONS;
	}
	
	
	
	
	
	
	
}