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
import com.common.entity.order.OrderBundle;
import com.common.exception.AccountCredentialException;
import com.common.type.OrderStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.OrderBundleService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;
import com.web.controller.page.admin.AbstractAdminBase;

@Controller("adminOrderBundleController")
public class OrderBundleController extends AbstractAdminBase {

	@Autowired private OrderBundleService orderBundleService;
	
	
	@RequestMapping(value=UriAdminRequestMapping.Order.ORDER_BUNDLES
								, method=RequestMethod.GET)
	public String showPage(Model model
											, @ActiveUserPrincipal Account account
											, @RequestParam(defaultValue="0") int _p
											, @RequestParam(defaultValue="10") int _m
											, @RequestParam(defaultValue="all") String _s
								) throws AccountCredentialException 
	{
		isAdmin(account);
		
		OrderStatus status = OrderStatus.lookup(_s);
		DateInterval dateInterval = null;
		Pageable pageable = new PageSearch(_p, _m);
		List<OrderBundle> orders = orderBundleService.getOrderBundle(status, dateInterval, pageable );
		
		
		Map<String, Object> params = new HashMap<>();
		long pending = orderBundleService.countOrderBundles(OrderStatus.PENDING, null);
		long completed = orderBundleService.countOrderBundles(OrderStatus.COMPLETED, null);
		params.put("pending", pending);
		params.put("completed", completed);
		params.put("all", pending + completed);
		
		model.addAttribute("orders", orders);
		model.addAttribute("params", params);
		model.addAttribute("status", status);
		return PageAdvice.Admin.Order.ORER_BUNDLE_PAGE;
	}
	
}
