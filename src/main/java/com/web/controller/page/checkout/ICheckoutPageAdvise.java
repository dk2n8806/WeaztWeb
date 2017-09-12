package com.web.controller.page.checkout;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.ProductNotFoundException;
import com.web.response.JsonResponse;

public interface ICheckoutPageAdvise {

	/** {@link CheckoutPageController#showCheckoutPage(Model, Account, Long)} */
	String showCheckoutPage(Model model, Account account, long _pid) throws ProductNotFoundException;
	
	/** {@link CheckoutPageController#showCheckoutPage(Model, Account)} */
	void showCheckoutPage(Model model, Account account);
	
	/** {@link CheckoutPageController#showCheckoutErrorPage(Model)} */
	String showCheckoutErrorPage(Model model);
	
	/** {@link CheckoutPageController#showCheckoutFailPage(Model)} */
	String showCheckoutFailPage(Model model) ;
	
	
	
	
	
	/** {@link CheckoutPageController#processCheckout(Account, long, int, int)} */
	JsonResponse processCheckout(Account account, long _pid, int frequency, int numberOfSubscription, String promotionCode);
	
	
	/** {@link CheckoutPageController#updateCheckoutAddress(Account, long, String, String, String, String, String)} 
	 * @throws ProductNotFoundException */
	JsonResponse updateCheckoutAddress(Account account, long productId
									, String _name
									, String _address, String city
									, String _state, String _zipcode) throws ProductNotFoundException;
	
	
	/** {@link CheckoutPageController#updatePaymentInfo(Account, String, String, Integer, Integer, String, String)} */
	JsonResponse updatePaymentInfo(Account account, String _tokenId);
	
	
	/** {@link CheckoutPageController#updatePromotion(Account, String)} */
	JsonResponse updatePromotion(Account account, String promotion);
	
	
	
}
