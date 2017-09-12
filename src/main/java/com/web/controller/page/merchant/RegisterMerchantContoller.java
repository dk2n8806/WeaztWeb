package com.web.controller.page.merchant;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.entity.account.Account;
import com.common.entity.address.Address;
import com.common.entity.merchant.Merchant;
import com.common.entity.support.embeded.Phone;
import com.common.exception.AccountException;
import com.common.exception.MerchantException;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.type.USAStates;
import com.core.service.account.AccountService;
import com.core.service.merchant.MerchantService;
import com.core.service.shippo.ShippoAddressService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;

@Controller
public class RegisterMerchantContoller 
extends AbstractMerchantBaseController implements IRegisterMerchantController {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private MerchantService merchantService;
	@Autowired private ShippoAddressService shippoAddressService;
	@Autowired private AccountService accountService;
	
	
	/** :::
	 * <p>Display merchant register form</p>
	 * 
	 * {@link IRegisterMerchantController#showRegisterForm(Model, Account)}
	 * ::: */
	@RequestMapping(value={UriPageRequestMapping.Merchant.NEW_MERCHANT, "/partner"}
										, method=RequestMethod.GET)
	@Override
	public String showRegisterForm(Model model
								, @ActiveUserPrincipal Account account)
										throws MerchantException
	{
		System.out.println();
		logger.info("Show new merchant application form");
		
		try {
//			if(account.getStatus().equals(AccountStatus.DEACTIVE))
//				throw new IllegalArgumentException("Invalid account state");
//			if(!account.getRole().equals(Role.CUSTOMER)) 
//				throw new IllegalArgumentException("Invalid account role");
//			
			
			//return PageAdvice.Merchant.NEW_MERCHANT_FORM;
			return "/user/form/partnerIndex";
		} catch(IllegalArgumentException e) {
			return PageAdvice.Merchant.MERCHANT_FORM_ERROR;
		}
	}


	@RequestMapping(value="/user/welcome-partner")
	public String success(Model model, @ActiveUserPrincipal Account account) {
		try {
			if(account.getStatus().equals(AccountStatus.DEACTIVE))
				throw new IllegalArgumentException("Invalid account status");
			if(!account.getRole().equals(Role.MERCHANT)) 
				throw new IllegalArgumentException("Invalid account role");
			
			return "/user/form/merchantApplicationSuccess";
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			throw new GeneralResourceNotFoundException();
		}
	}


	/** :::
	 * <p>Process the merchant register form</p>
	 * <br>
	 * <p>Capture merchant basic info such as business name, phone and address.</p>
	 * {@link IRegisterMerchantController#createNewMerchant}
	 */
	@RequestMapping(value=UriActionRequestMapping.Merchant.CREATE_NEW_MERCHANT
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	createNewMerchant(@ActiveUserPrincipal Account account
																		, @RequestParam String _bname
																		, @RequestParam String _baddress
																		, @RequestParam String _bcity
																		, @RequestParam String _bstate
																		, @RequestParam String _bzipcode
																		, @RequestParam String _bphone
																		, @RequestParam(required=false) String _bwebsite
																		) 
	{
		
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				if(account.getStatus().equals(AccountStatus.DEACTIVE))
					throw new IllegalArgumentException("Invalid account state");
				if(!account.getRole().equals(Role.CUSTOMER)) 
					throw new IllegalArgumentException("Invalid account role");
				
				Address address = shippoAddressService.create(
												new AddressAdapterAttribute(_bname, _baddress, _bcity
																			, USAStates.lookup(_bstate), Address.COUNTRY_DEFAULT
																			, _bzipcode));
				
				Assert.notNull(address, "Invalid address! Please make sure you enter correct address.");
				Merchant merchant = merchantService.save(account, _bname, _bwebsite
																				, Phone.create(_bphone), address);
				
				Assert.notNull(merchant, "Sorry, we are not able to process your application this time. "
													 + "Please contact us this problem continue occurs");
				
				account.setMerchant(merchant);
				account.setRole(Role.MERCHANT);
				
				accountService.promoteToMerchant(account.getId());
				
				jsonResponse.setState(true);
				jsonResponse.setResult("/user/welcome-partner");
			} catch(IllegalArgumentException | AccountException e) {
				e.printStackTrace();
				jsonResponse.setResult(e.getMessage());
				jsonResponse.setState(false);
				
			}
			return jsonResponse;
		
		}
	}
	
	
	
}
