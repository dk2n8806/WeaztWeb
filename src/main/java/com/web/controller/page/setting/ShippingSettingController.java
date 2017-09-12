package com.web.controller.page.setting;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.common.type.USAStates;
import com.common.util.PageSearch;
import com.core.service.account.SimpleShippingService;
import com.core.service.shippo.ShippoAddressService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class ShippingSettingController implements IShippingAdvice{

	private static final Logger logger = LogManager.getLogger();
	@Autowired private SimpleShippingService shippingService;
	@Autowired private ShippoAddressService shippoService;
	
		
	/** :::
	 * <p>Display account shipping info of the client</p>
	 * 
	 * {@link IShippingAdvice#showPage(Model, Account)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Setting.SHIPPING_SETTING
					, method=RequestMethod.GET)	
	@Override
	public String showPage(Model model
						, @ActiveUserPrincipal Account customer) 
	{
		System.out.println();
		logger.info("Retrieve a list of shipping_info by the customer");
		List<SimpleShipping> shippings = null;
		boolean isActive = true;
		Boolean isPrimary = null;
		long size = shippingService.countByAccount(customer, isActive );
		if(size > 0) {
			Pageable pageable = new PageSearch(0, size);
			shippings = shippingService.getByAccount(customer, isPrimary, isActive, pageable);
		}
		model.addAttribute("shippings", shippings);
		return PageAdvice.Setting.SHIPPING_SETTING;
	}
	
		
	/** :::
	 * <p>Handle client request to create a new shipping info</p>
	 * 
	 * {@link IShippingAdvice#createShippingInfo(Account, String, String, String, String, String, String)}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.CREATE_SHIPPING_INFO
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse createShippingInfo(@ActiveUserPrincipal Account customer
								, @RequestParam(name="_name") String name
								, @RequestParam(name="_street") String street
								, @RequestParam(name="_city") String city
								, @RequestParam(name="_state") String state
								, @RequestParam(name="_name") String zipcode
								, @RequestParam(required=false)	String country) {
		System.out.println();
		logger.info("Create a new shipping_info by the customer");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			AddressAdapterAttribute attribute = new AddressAdapterAttribute(
																	name, street, city, USAStates.lookup(state), zipcode);
			Address address = shippoService.create(attribute);
			Assert.notNull(address, "Unable to create new address");
			
			SimpleShipping shipping = shippingService.save(customer, address);
			Assert.notNull(shipping, "Unbale to create a new shipping_info");
			jsonResponse.setState(true);
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			jsonResponse.setState(false);
			jsonResponse.setResult(e.getMessage());
		}
		return jsonResponse;
	}

	
	/** :::
	 * <p>Handle request by the client to set a shipping to primary</p>
	 * 
	 * {@link IShippingAdvice#setPrimary(Account, Long)}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.SET_PRIMARY_SHIPPING
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse setPrimary(@ActiveUserPrincipal Account customer
						, @RequestParam(name="id") Long shippingId) 
	{
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			try {
				SimpleShipping shipping = shippingService.getByAccount(shippingId, customer);
				
				Assert.notNull(shipping, "Unable to look up client's shipping info");
				shippingService.setPrimary(shipping);
				jsonResponse.setState(true);
			} catch(IllegalArgumentException e) {
				logger.error("[ERROR]" + e.getMessage());
				jsonResponse.setState(false);
				jsonResponse.setResult(e.getMessage());
			}
			return jsonResponse;
		}
	}



		
	/** :::
	 * <p>Handle client request to delete a shipping info</p>
	 * 
	 * {@link IShippingAdvice#delete(Account, Long)}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.DELETE_SHIPPING
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse delete(@ActiveUserPrincipal Account customer
							, @RequestParam(name="id") Long shippingId) 
	{
		System.out.println();
		logger.info("Delete a shipping_info by a customer");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			SimpleShipping shipping = shippingService.getByAccount(shippingId, customer);
			
			Assert.notNull(shipping, "Unable to lookup shipping with id [" + shippingId + "]");
			Assert.isTrue(!shipping.isPrimary(), "Unable to delete the address. A primary address is required.");
			shippingService.deactive(shipping);
			jsonResponse.setState(true);
			
		} catch(IllegalArgumentException e) {
			logger.error("[ERROR]" + e.getMessage());
			jsonResponse.setState(false);
			jsonResponse.setResult(e.getMessage());
		}
		return jsonResponse;
	}

}
