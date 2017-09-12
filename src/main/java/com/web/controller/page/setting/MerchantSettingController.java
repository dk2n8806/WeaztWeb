package com.web.controller.page.setting;

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
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.TaxRate;
import com.common.exception.MerchantException;
import com.common.type.USAStates;
import com.core.service.merchant.MerchantProfileService;
import com.core.service.merchant.MerchantService;
import com.core.service.shippo.adapter.AddressAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;
import com.web.response.JsonResponse;


@Controller
public class MerchantSettingController
extends AbstractMerchantBaseController implements IMerchantSettingAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private MerchantService merchantService;
	@Autowired private MerchantProfileService merchantContactService;
	@Autowired private AddressAdapterService shippoAddressService;
	
	
	
	/** :::
	 * <p>Display Merchant info</p>
	 * 
	 * {@link IMerchantSettingAdvise#showSettingPage}
	 * @throws MerchantException 
	 */
	@RequestMapping(value=UriPageRequestMapping.Setting.MERCHANT_SETTING
				, method=RequestMethod.GET)
	@Override
	public String showSettingPage(Model model
								, @ActiveUserPrincipal Account customer) throws MerchantException 
	{
		try {
			final Merchant merchant = getAuthorizedMerchant(customer);
			MerchantProfile profile = merchantService.getProfile(merchant);
			Assert.notNull(profile, "Unable to retrieve contact of the merchant");
			//TaxRate taxRate = merchantService.getTaxRate(merchant);
			model.addAttribute("merchant", merchant);
			model.addAttribute("profile", profile);
			//model.addAttribute("taxRate", taxRate);	
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return PageAdvice.Setting.MERCHAT_SETTING;
	}
	

	
	/** :::
	 * <p>Update merchant info</p>
	 * 
	 * {@link IMerchantSettingAdvise#updateContact}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_MERCHANT_INFO
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	updateContact(@ActiveUserPrincipal Account customer
																, @RequestParam(defaultValue="") String _company
																, @RequestParam(defaultValue="") String _website
																, @RequestParam(defaultValue="") String _phone
																, @RequestParam(defaultValue="") String _street
																, @RequestParam(defaultValue="") String _city
																, @RequestParam(defaultValue="") String _state
																, @RequestParam(defaultValue="") String _zipcode) 
	{
		try {
			Merchant merchant = this.getAuthorizedMerchant(customer);
		} catch (MerchantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonResponse jsonResponse = new JsonResponse();
//		try {
//			System.out.println("" + _company);
//			System.out.println("" + _website);
//			System.out.println("" + _phone);
//			System.out.println("" + _street);
//			System.out.println("" + _city);
//			System.out.println("" + _state);
//			System.out.println("" + _zipcode);
//			
//			Assert.isTrue(_company.length() >= 3);
//			
//			Address address = shippoAddressService.create(new AddressAdapterAttribute(
//																			_company, _street, _city, USAStates.lookup(_state), _zipcode));
//			Assert.notNull(address, "Address can't be null");
//			
//			Phone phone = Phone.create(_phone);
//			Assert.notNull(phone, "Phone can't be null");
//			
//			Assert.notNull(merchant, "Unable to retrieve merchant data");
//			
//			MerchantProfile merchantContact = merchantService.getProfile(merchant);
//			Assert.notNull(merchantContact, "Unable to retrieve merchant_contact data");
//			
//			AddressAdapterAttribute adapterAttribute = new AddressAdapterAttribute(merchantContact.getAddressAdapter().getName(), address);
//			AddressAdapter adapter = shippoAddressService.create(adapterAttribute);
//					
//			merchantContact.setPhone(phone);
//			merchantContact.setAddressAdapter(adapter);
//			merchantContactService.update(merchantContact);
//			logger.info("Successful updated merchant_contact");
//			
//			merchant.setBusinessName(_company);
//			merchant.setBusinessUrl(_website);
//			merchantService.update(merchant);
//			logger.info("Succefful updated merchant info");
//			
//			jsonResponse.setState(true);
//			
//		} 
//		catch(IllegalArgumentException e) {
//			jsonResponse.setState(false);
//			e.printStackTrace();
//		}
//		catch (AuthenticationException | InvalidRequestException
//				| APIConnectionException | APIException e) {
//			e.printStackTrace();
//		}
//		catch (MerchantException e) {
//			jsonResponse.setState(false);
//			e.printStackTrace();
//		}
//		logger.info("status: " + jsonResponse.isState());
		return jsonResponse;
	}



/*********************************************************************
 * Update merchant info
 * 
 * {@link IMerchantSettingAdvise#updateContact}
 */
//	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_MERCHANT_TAX
//					, method=RequestMethod.POST)
//	@ResponseBody
//	@Override
//	public JsonResponse updateTax(@ActiveUserPrincipal Account customer
//								, @RequestParam Long _rate) {
//		JsonResponse jsonResponse = new JsonResponse();
//		try {
//			final Merchant merchant = getAuthorizedMerchant(customer);
//			TaxRate taxRate = taxRateService.getByMerchant(merchant);
//			taxRate.setTaxRate(_rate);
//			taxRateService.updateTax(taxRate);
//			jsonResponse.setState(true);
//		} catch(MerchantException e) {
//			jsonResponse.setState(false);
//			e.printStackTrace();
//		}
//		return jsonResponse;
//	}
//	
	
}
