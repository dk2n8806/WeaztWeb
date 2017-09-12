package com.web.controller.page.subscription;

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
import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.SubscriptionStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.dto.SubscriptionTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


@Controller
public class SubscriptionController  {

	private static final Logger logger = LogManager.getLogger();
	
	
	
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private SubscriptionTemplateDTOService templateService;
	
	
		
	/** :::
	 * <p>Retrieve and display a list of subscription by the account</p>
	 * 
	 */
	@RequestMapping(name=UriPageRequestMapping.User.Subscription.SUBSCRIPITONS
					, method=RequestMethod.GET)
	public String showPage(Model model
												, @ActiveUserPrincipal Account account
												, @RequestParam(name="p", defaultValue="0") int pageNumber
												, @RequestParam(name="s", defaultValue="20") int pageSize
												, @RequestParam(name="ref", defaultValue="") String _status
						)
	
	{
		System.out.println();
		logger.info("Retrieve a list of subscritpions by a account");
		DateInterval dateInterval = null;
		List<SubscriptionTemplateDTO> subscriptions = null;
		Pageable page = new PageSearch((pageNumber < 0) ? 0 : pageNumber, pageSize);
		SubscriptionStatus status = SubscriptionStatus.SUBSCRIBED;
		
		long size = subscriptionService.countByAccount(account, status, dateInterval);
		if(size > 0) {
			subscriptions = templateService.getByAccount(account, status, dateInterval, page);
		}
	
		
		model.addAttribute("subscriptions", subscriptions);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		return PageAdvice.User.SUBSCRIPTION;
	}
	
	
	
}
