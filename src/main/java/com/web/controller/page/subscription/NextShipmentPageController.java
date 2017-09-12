package com.web.controller.page.subscription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.shipment.RecurrentSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.OrderIntentService;
import com.core.service.shipment.dto.NextShipmentTemplateService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class NextShipmentPageController {

	@Autowired private NextShipmentTemplateService shipmentTemplateService;
	@Autowired private OrderIntentService orderIntentService;
	
	
/**
 * 
 */
	@RequestMapping(value=UriPageRequestMapping.User.Subscription.NEXT_SHIPMENT
					, method=RequestMethod.GET)
	public String showPage(Model model
						, @ActiveUserPrincipal Account account
						, @RequestParam(name="p", defaultValue="0") int pageNumber
						, @RequestParam(name="s", defaultValue="20") int pageSize)
	{
	
		DateInterval dateInterval = null;
		long count = orderIntentService.countScheduledByAccount(account,  dateInterval );
		List<RecurrentSubscriptionShipmentTemplateDTO> templates = new ArrayList<>();
		Pageable page = new PageSearch((pageNumber < 0) ? 0 : pageNumber, pageSize);
		if(count > 0) {
			templates = shipmentTemplateService.getByAccount(account, page);
		}

		model.addAttribute("page", page);
		model.addAttribute("count", count);
		model.addAttribute("templates", templates);
		return PageAdvice.User.Shipment.NEXT_SHIPMENTS;
	}
}
