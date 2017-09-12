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
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.OrderTransactionService;
import com.core.service.shipment.dto.LastSubscriptionShipmentTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;


/** 
 * <p>Subscription shipments of the client</p>
 * 
 * @author dk2n_
 *
 */
@Controller
public class HistoryShipmentPageController {


	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private LastSubscriptionShipmentTemplateDTOService shipmentTemplateService;
	@Autowired private OrderTransactionService ordertransactionService; 


	
	
	/** :::
	 * <p>Display subscription order transaction</p>
	 * 
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.User.Subscription.HISTORY_SHIPMENT
					, method=RequestMethod.GET)
	public String showPage(Model model
						, @ActiveUserPrincipal Account account
						, @RequestParam(name="p", defaultValue="0") int pageNumber
						, @RequestParam(name="s", defaultValue="20") int pageSize) 
	{
		logger.info("show page");
		DateInterval dateInterval = null;
		long count = ordertransactionService.countByAccount(account, dateInterval );
		List<LastSubscriptionShipmentTemplateDTO> templates = new ArrayList<>();
		Pageable page = new PageSearch((pageNumber < 0) ? 0 : pageNumber, pageSize);
		if(count > 0) {
			templates = shipmentTemplateService.getHistoryShipmentsByAccount(
																account, page);
		}
		
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		model.addAttribute("templates", templates);
		return PageAdvice.User.Shipment.HISTORY_SHIPMENTS;
	}
}
