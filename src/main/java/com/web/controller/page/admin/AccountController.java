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
import com.common.dto.AccountTemplateDTO;
import com.common.entity.account.Account;
import com.common.exception.AccountCredentialException;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.PageSearch;
import com.core.service.account.AccountService;
import com.core.service.account.dto.AccountTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriAdminRequestMapping;

@Controller("adminAccountController")
public class AccountController extends AbstractAdminBase  {

	

	@Autowired private AccountService accountService;
	@Autowired private AccountTemplateDTOService templateService;
	
		
	/**
	 * <p>Display a list of account templates</p>
	 * 
	 * {@link IAdminPageAdvise#showMainAdminPage(Model, Account)}
	 */
	@RequestMapping(value=UriAdminRequestMapping.DISPLAY_CUSTOMERS
								, method=RequestMethod.GET)
	public String showMainAdminPage(Model model
								, @ActiveUserPrincipal Account customer
								, @RequestParam(name="_role", defaultValue="all") String _role
								, @RequestParam(required=false) String _status
								, @RequestParam(defaultValue="0")		 int _p
								, @RequestParam(defaultValue="10") 	int _m
								) 
										throws AccountCredentialException 
	{
		isAdmin(customer);	
		AccountStatus status = AccountStatus.lookup(_status);
		Role role = Role.lookup(_role);
		
		
		long countAll = accountService.getRowCount();
		long countAdmin = accountService.countAccount(Role.ADMIN, status, null);
		long countCustomer = accountService.countAccount(Role.CUSTOMER, status, null);
		long countMerchant = accountService.countAccount(Role.MERCHANT, status, null);
		
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("all", countAll);
		params.put("admin", countAdmin);
		params.put("customer", countCustomer);
		params.put("merchant", countMerchant);
		
		
		Pageable pageable = new PageSearch(_p, _m);
		List<AccountTemplateDTO> accounts = templateService.getTemplates(role, status, null, pageable);
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("params", params);
		model.addAttribute("role", _role);
		model.addAttribute("_status", status);
		return PageAdvice.Admin.DISPLAY_CUSTOMERS;
	}
	
	
	
	
	

	
}
