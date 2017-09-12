package com.web.advice;

import org.springframework.ui.Model;

import com.web.controller.page.admin.CategoryController;
import com.web.controller.page.admin.ProductController;
import com.web.controller.page.admin.SubscriptionController;
import com.web.controller.page.admin.order.OrderBundleController;
import com.web.controller.page.admin.order.OrderIntentController;
import com.web.controller.page.admin.order.OrderTransactionController;
import com.web.controller.page.admin.payment.PaymentController;
import com.web.controller.page.admin.payout.PayoutController;

public interface UriAdminRequestMapping {

	/** {@link DashboardController#showMainAdminPage} */
	String DASHBOARD = "/user/admin/display";
	
	
	/** {@link MessageCenterController#showMainAdminPage} */
	String MESSAGE_CENTER = "/user/admin/messages-center";

	
// ::: CUSTOMERS ::: 
	
	/** {@link AccountController#showMainAdminPage(Model, Account)} */
	String DISPLAY_CUSTOMERS 		= "/user/admin/display/accounts";
	

	
	
	/** {@link CategoryController} */	
	interface Category {
		String DISPLAY_CATEGORIES 	= "/user/admin/display/categories";
		String TOGGLE_STATUS 			= "/user/admin/toggle-category";
		String CREATE_CATEGORY 		= "/user/admin/create-category";
	}
	
	

	interface PRODUCT {

		/** ::: {@link ProductController#showPage} */
		String DISPLAY_PRODUCTS 		= "/user/admin/display/products";
	}
	

	interface Subscription {
		/** ::: {@link SubscriptionController#showPage} */
		String DISPLAY_SUBSCRIPTIONS 	= "/user/admin/display/subscriptions";
	}
	
	/** {@link UnsubscribeRequestController#showRequest(Model, Account)} */
	String DISPLAY_UNSUBSCRIBED_REQUESTS = "/user/admin/display/unsubscribe_requests";
	
	
	
	interface Order {

		/** ::: {@link OrderIntentController#showPage} */
		String DISPLAY_ORDER_INTENTS 	= "/user/admin/display/order_intents";
		
		/** ::: {@link OrderBundleController#showPage} */
		String ORDER_BUNDLES = "/user/admin/display/orders";
		
		/** ::: {@link OrderTransactionController#showPage} */
		String ORDER_TRANSACTIONS = "/user/admin/display/order/{_orderBundleId}/transactions";
	}
	
	interface Payment {
		
		/** ::: {@link PaymentController#showPage} */
		String PAYMENT 				= "/user/admin/display/payments";
		
		/** {@link PaymentController#charge} */
		String CHARGE_PAYMENT = "/user/admin/charge-payments";
	}
	
	
	interface Payout {
		/** ::: {@link PayoutController#showPage} */
		String PAYOUTS 					= "/user/admin/display/payouts";
	}
	
//-----
// Orders
//----
	
	
	/** {@link OrderController#showMainAdminPage(Model, Account, String)} */
	String DISPLAY_ORDERS 			= "/user/admin/display/orders";
	
	/** {@link OrderRequestController#showMainAdminPage(Model, Account, String)} */
	String DISPLAY_ORDER_REQUESTS = "/user/admin/display/order_requests";
	
	/** {@link OrderRequestController#process(Account)} */
	String PROCESS_ORDER_SCHEUDLE = "/user/admin/process-order-schedule";
	
//----
// Shipmens
//----
	/** {@link ShipmentController#showMainAdminPage(Model, Account, String)} */
	String DISPLAY_SHIPMENT 		= "/user/admin/display/shipments";
	

//----
// Payout
//---
	
	/** {@link PayoutController#runDeposite(Account)} */
	String PROCESS_PAYOUT_SCHEDULE = "/user/admin/process-payout-schedule";
	
	
	
	

//----
// Payments
//---
	/** {@link PaymentRequestController#showMainAdminPage(Model, Account, String)} */
	String PAYMENT_REQUESTS 		= "/user/admin/display/payment_requests";

	/** {@link PaymentRequestController#processPaymentRequest(Account)} */
	String PROCESS_PAYMENT_SCHEDULE = "/user/admin/process-payment-schedule";


}
