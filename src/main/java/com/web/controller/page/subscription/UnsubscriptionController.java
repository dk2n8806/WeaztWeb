package com.web.controller.page.subscription;

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
import com.common.dto.subscription.UnsubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.subscription.UnsubscriptionService;
import com.core.service.subscription.dto.UnsubscriptionTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class UnsubscriptionController implements IUnsubscriptionAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private UnsubscriptionTemplateDTOService templateService;
	@Autowired private UnsubscriptionService unsubscriptionService;
	
	private final int PAGE_SIZE = 8;
	
/**
 * Display a list of unsubscription by the account
 * 
 * {@link IUnsubscriptionAdvise#showPage(Model, Account, String, String, int)}
 */
	@RequestMapping(value=UriPageRequestMapping.User.Subscription.UNBSCRIPTION
				, method=RequestMethod.GET)
	@Override
	public String showPage(Model model
						, @ActiveUserPrincipal Account account
						, @RequestParam(required=false) String _status
						, @RequestParam(required=false) String _role
						, @RequestParam(defaultValue="0", required=false) int _p)
	{
		System.out.println();
		logger.info("Display a list of unsubscriptions");
		DateInterval dateInterval = null;
		long size = unsubscriptionService.countByAccount(account, dateInterval);
		List<UnsubscriptionTemplateDTO> templates = new ArrayList<UnsubscriptionTemplateDTO>();
		if(size > 0) {
			Pageable pageable = new PageSearch(_p, PAGE_SIZE);
			templates = templateService.getByAccount(account,  dateInterval, pageable );
		}
		model.addAttribute("size", size);
		model.addAttribute("templates", templates);
		
		return PageAdvice.User.UNSUBSCRIPTIONS;
	}
	

}
