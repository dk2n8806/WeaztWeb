package com.web.controller.page.order;


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
import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.exception.MerchantException;
import com.common.type.OrderStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.MerchantOrderBundleService;
import com.core.service.order.dto.OrderBundleWrapperDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class OrderTransactionController
extends AbstractOrderHelper implements IOrderTransactionAdvise{

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private MerchantOrderBundleService merchantOrderService;
	@Autowired private OrderBundleWrapperDTOService templateWrapperService;
	
		
	/**
	 * <p>Display a list of order_transactions</p>
	 * 
	 * {@link IOrderTransactionAdvise#showOrderTransactions(Model, Account, String,  Integer)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Order.ORDER_TRANSACTIONS
					, method=RequestMethod.GET)
	@Override
	public String showOrderTransactions(Model model
									, @ActiveUserPrincipal Account customer
									, @RequestParam(required=false) String _ref
									, @RequestParam(defaultValue="0") Integer _p) 
	{
		logger.info("Display order histories");
		try {
			final Merchant merchant = this.getAuthorizedMerchant(customer);
			long completedCount = 0;
			long pendingCount = 0;
			OrderStatus status = OrderStatus.lookup(_ref);
			
			completedCount = merchantOrderService.countByMerchant(merchant, OrderStatus.COMPLETED, null);
			pendingCount = merchantOrderService.countByMerchant(merchant, OrderStatus.PENDING, null);
			Pageable pageable = new PageSearch((_p < 0) ? 0 : _p, 10);
			DateInterval dateInterval = null;
			List<OrderBundleWrapperDTO> orders = templateWrapperService.getByMerchant(merchant, status, dateInterval , pageable);
			
			model.addAttribute("_ref", _ref);
			model.addAttribute("_p", _p);
			model.addAttribute("orders", orders);
			model.addAttribute("completedCount", completedCount);
			model.addAttribute("pendingCount", pendingCount);
		} catch(IllegalAccessError e){
			logger.warn(e.getMessage());
		} catch(MerchantException e) {
			logger.error("The account is not merchant_account");
		}
		return PageAdvice.Merchant.Order.ORDER_TRANSACTIONS;
	}

}
