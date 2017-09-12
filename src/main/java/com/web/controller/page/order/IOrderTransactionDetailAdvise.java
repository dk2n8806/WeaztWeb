package com.web.controller.page.order;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;

public interface IOrderTransactionDetailAdvise {

	/** 
	 * Customer request to download a shipment label on a specified shipment_id 
	 * @request {@link OrderTransactionDetailController#downloadLabel(Account, Long, HttpServletResponse)} */
	void downloadLabel(Account customer, Long shipmentId, HttpServletResponse response) 
															throws MerchantException;



}
