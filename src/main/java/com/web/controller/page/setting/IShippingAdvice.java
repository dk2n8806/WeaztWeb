package com.web.controller.page.setting;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.web.response.JsonResponse;

public interface IShippingAdvice {

	/** Show shipping_info by a customer
	 * {@link ShippingSettingController#showPage}*/
	String showPage(Model model, Account customer);
	
	
	/** Create a new shipping_info by a customer
	 * {@link ShippingSettingController#createShippingInfo} */
	JsonResponse createShippingInfo(Account customer, String name
							, String street, String city
							, String state, String zipcode, String country);
	
	
	/** Set a shipping_info as primary
	 * {@link ShippingSettingController#setPrimary} */
	JsonResponse setPrimary(Account customer, Long shippingId);
	
	
	/** Delete a shipping_info by a customer
	 * {@link ShippingSettingController#delete} */
	JsonResponse delete(Account customer, Long shippingId);
}
