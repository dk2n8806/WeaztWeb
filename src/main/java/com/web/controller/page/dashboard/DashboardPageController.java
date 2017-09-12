package com.web.controller.page.dashboard;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.exception.MerchantException;
import com.common.type.OrderIntentStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.product.dto.BookmarkTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author dk2n_
 *
 */
@Controller
public class DashboardPageController 
extends AbstractMerchantBaseController implements IDashboardAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private MerchantOrderIntentSerivce orderService;
	@Autowired private BookmarkTemplateDTOService bookmarkTemplateDTOService;
	
	
	/** :::
	 * <p>Display dashboard page of the client</p>
	 * 
	 * {@link IDashboardAdvise#showPage}
	 */
	@RequestMapping(value=UriPageRequestMapping.User.DashBoard.DASHBOARD_PAGE
								, method=RequestMethod.GET)
	public String showPage(Model model
										, @ActiveUserPrincipal Account account
										, @CookieValue(name="ll", required=false) String location) 
						throws MerchantException 
	{	
		Merchant merchant = getAuthorizedMerchant(account);
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), -7);
		
		
		long orderSize = 0;
		if(merchant != null) {
			orderSize = orderService.countByMerchant(merchant, OrderIntentStatus.REQUESTING, dateInterval);
		}
		
		model.addAttribute("bookmarks", 
				bookmarkTemplateDTOService.getByAccount(account, null, new PageSearch(0, 2)));
		model.addAttribute("orderSize", orderSize);
		model.addAttribute("merchant", account.getMerchant());
		return PageAdvice.User.DASHBOARD;
	}

	/*
	NotificationStatus status = NotificationStatus.NEW;
	
	List<AccountNotification> accNos = new ArrayList<AccountNotification>();
	List<MerchantNotification> merNos = new ArrayList<MerchantNotification>();
	long accNoSize  =  0;
	long merNoSize = 0;
	
	try {
		accNoSize = accountNotificationService.countByAccount(account, status, dateInterval );
	} catch(NullPointerException e) {
		accNoSize = 0;
	}
	if(accNoSize > 0) {
		Pageable pageable = new PageSearch(accNoSize);
		accNos = accountNotificationService.getByAccount(account, status, dateInterval, pageable );
	}	
	
	// TODO
	// Count total order_intent that the merchant need to process today
	
	if(merchant != null) {
		try {
			merNoSize = merchantNotificationService.countByMerchant(merchant, status, dateInterval);
		} catch(NullPointerException e) {
			merNoSize = 0;
		}
		if(merNoSize > 0) {
			Pageable pageable = new PageSearch(merNoSize);
			merNos = merchantNotificationService.getByMerchant(merchant, status, dateInterval, pageable );
		}
		
	}
	merchantNotificationService.markAsRead(merNos, NotificationType.AUTO_DELETE);
	accountNotificationService.markAsRead(accNos, NotificationType.AUTO_DELETE);
	
	model.addAttribute("accNoSize", accNoSize);
	model.addAttribute("accNos", accNos);
	model.addAttribute("merNoSize", merNoSize);
	model.addAttribute("merNos", merNos);
	*/
	
}
