package com.web.controller.page.order;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;

public interface IOrderIntentDetailAdvise {

	/** {@link OrderIntentDetailPageController#showCustomerDetailOnProduct(Model, Account, Long, String)} 
	 * @throws MerchantException */
	String showCustomerDetailOnProduct(Model model, Account customer
										, Long _productId, String _date) throws MerchantException;


	/** {@link OrderIntentDetailPageController#processOrderIntents(Account, Long, String)} */
	JsonResponse processOrderIntents(Account customer, Long _productId, String _date);
	
	
	/** {@link OrderIntentDetailPageController#dequeueCustomerFromQueue(Account, Long, String)}  */
	JsonResponse dequeueCustomerFromQueue(
					Account customer, Long _orderId, String _quote) 
								throws MerchantException;

}
