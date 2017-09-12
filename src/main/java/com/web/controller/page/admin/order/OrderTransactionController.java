package com.web.controller.page.admin.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.exception.AccountCredentialException;
import com.common.util.PageSearch;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderTransactionService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.controller.page.admin.AbstractAdminBase;

public class OrderTransactionController extends AbstractAdminBase {

	@Autowired private OrderTransactionService orderTransactionService;
	@Autowired private OrderBundleService orderBundleService;
	
	
	@RequestMapping(value=UriAdminRequestMapping.Order.ORDER_TRANSACTIONS
								, method=RequestMethod.GET)
	public String showPage(Model model
											, @ActiveUserPrincipal Account account
											, @PathVariable Long _orderBundleId
								) throws AccountCredentialException 
	{
		isAdmin(account);
			try {
			
			OrderBundle order = orderBundleService.findById(_orderBundleId);
			Pageable pageable = new PageSearch(0, order.getNoo());
			
			List<OrderTransaction> orderTransactions = orderTransactionService.getByOrder(order, pageable);
			Assert.isTrue(order.getNoo() == orderTransactions.size());
			
			model.addAttribute("order", order);
			model.addAttribute("transaction", orderTransactions);
		} catch(NullPointerException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return PageAdvice.Admin.Order.ORDER_TRANSACTION_PAGE;
	}
	
}
