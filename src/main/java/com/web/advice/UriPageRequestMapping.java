package com.web.advice;

import org.springframework.ui.Model;

import com.web.controller.page.BrowsePageController;
import com.web.controller.page.account.LoginPageController;
import com.web.controller.page.dashboard.BookmarkPageController;
import com.web.controller.page.listing.RemoveListingRequestPageController;
import com.web.controller.page.listing.DeletedListingPageController;
import com.web.controller.page.listing.ListingDetailPageController;
import com.web.controller.page.listing.ListingPageController;
import com.web.controller.page.listing.NewListingPageController;
import com.web.controller.page.listing.PrivateListingPageController;
import com.web.controller.page.subscription.SubscriptionController;
import com.web.controller.page.subscription.SubscriptionDetailController;
import com.web.controller.page.transaction.PaymentMethodController;
import com.web.controller.page.transaction.PaymentTransactionController;
import com.web.controller.page.transaction.PayoutEarningController;
import com.web.controller.page.transaction.PayoutMethodController;
import com.web.controller.page.transaction.RefundPageController;
import com.web.controller.page.subscription.CurrentSubscritionRequestController;


public interface UriPageRequestMapping {
	
	
	public static final String  BASE = "/";
	
	
	interface Visit {
		/** {@link VisitMerchantProfileController#showMerchantProfile(Model, Long)} */
		String MERCHANT_PROFILE = "/show/merchants";
	}
	
	interface Shop {
		/** ::: {@link BrowsePageController#showIndexPage(Model, int, Long, Long)} */
		String SHOP = "/shop";		
		
		/** {@link SearchPageController#displaySearch} */
		String SEARCH = "/search";
		
		/** ::: {@link ProductController#displayProduct} */
		String PRODUCT = "/shop/product/{_pid}";	
	}
	
	/**
	 * Define mapping of user
	 */
	interface User {
		/** ::: {@link LoginPageController#showPage} ::: */
		String LOGIN 				= "/login";
		
		/** {@link RegisterPageController#showPage(Model)} */
		String REGISTER 			= "/register";
		
		/** {@link ForgotPasswordController#showForgotPasswordPage()} */
		String FORGOT_PASSWORD		= "/forgot_password";
		
		/** {@link ForgotPasswordController#showResetPasswordPage(Model, String, String)} */
		String RESET_PASSWORD 		= "/reset_password";
		
		
		/** {@link AccountVerificationController#processVerificationCode(Model, Account, Long, String)} */
		String VERIFIED_ACCOUNT = "/verify-email";
		

		
		
		interface Checkout {
			/**
			 * {@link CheckoutPageController#showCheckoutPage(Model, Account)}
			 * {@link CheckoutPageController#showCheckoutPage(Model, Account, long)}
			 */
			String CHECKOUT = "/user/checkout";
			
			/** {@link CheckoutPageController#showCheckoutErrorPage(Model)} */
			String CHECKOUT_ERROR = "/user/error/checkout";
			
			/** {@link CheckoutPageController#showCheckoutFailPage(Model)} */
			String CHECKOUT_FAIL = "/user/fail/checkout";
			
			
			/** {@link CheckoutConfirmPageController#showConfirmationPage(Model, Account, Long)} */
			String CHECKOUT_CONFIRM 		= "/user/confirm/checkout";
			
		}
		
		
		
		
		interface DashBoard {
			/** ::: {@link DashboardPageController#showDashboardPage(Model, Account)} */
			String DASHBOARD_PAGE 			= "/user/dashboard";
		}
		
		
		interface Subscription {

			
			/** ::: {@link SubscriptionController#showSubscriptions} */
			String SUBSCRIPITONS 								= "/user/subscriptions";						// Display a list of subscriptions of an account
			
			/** ::: {@link CurrentSubscritionRequestController#showPage} */
			String SUBSCRIPTION_REQUESTS 			= "/user/subscriptions/requests";
			
			
			/** ::: {@link SubscriptionDetailController#showSubscription}*/
			String SUBSCRIPTION_DETAIL_PAGE = "/user/subscriptions/{_sid}";		// Display a subscription info in detail
			
		

//=====
// Unsubscribe Request
//=====
			/** {@link UnSubscribeRequestController#showUnsubscribePage(Model, Account, String)} */
			String UNSUBSCRIBE_REQUEST_PAGE = "/user/subscriptions/unsubscribe-confirm";

			

//=====
// Unsubscriptions
//=====s
			/** {@link UnsubscriptionController#showPage(Model, Account, String, String, int)} */
			String UNBSCRIPTION = "/user/unsubscriptions";
			
			
			
			//=====
			// Subscription Shipments
			//=====
			
			/** {@link HistoryShipmentPageController#showPage} */
			String HISTORY_SHIPMENT = "/user/subscriptions/shipments";

			/** {@link NextShipmentPageController#showPage} */
			String NEXT_SHIPMENT 	= "/user/subscriptions/schedules";
			
			
			
		}
	}
	
	
	interface Setting {
		/** {@link AccountSettingController#displayGeneralAccountSetting} */
		String ACCOUNT_SETTING 		= "/user/setting/account";
		
		/** {@link ProfileSettingController#showPage(Model, Account)} */
		String PROFILE_SETTING 		= "/user/setting/profile";
		
		/** {@link ShippingSettingController#showPage} */
		String SHIPPING_SETTING 	= "/user/setting/account/shipping";
		
		
		/** {@link PasswordSettingController#showPasswordSettingPage} */
		String PASSWORD_SETTING     = "/user/setting/security";		
		
		/** {@link MerchantSettingController#showSettingPage} */
		String MERCHANT_SETTING		= "/user/setting/merchant";
		
		/** {@link AccountVerificationController#showAccountVerificationPage(Model, Account)} */
		String VERIFICATION_SETTING	= "/user/setting/verification";
	}
	
	
	
	interface Transaction {
		/** :: {@link PaymentMethodController#showPaymentMethods} */
		String PAYMENT 				= "/user/payment";
		
		/** ::: {@link PaymentTransactionController#showTransactions} */
		String PAYMENT_TRANSACTION 	= "/user/payment/transactions";
		
		/** ::: {@link RefundPageController#showPage} */
		String PAYMENT_REFUND = "/user/payment/refunds";
		
		
		
		/** ::: {@link PayoutMethodController#showPayoutMethods} */
		String PAYOUT 				= "/user/payout";
		
		/** ::: {@link PayoutEarningController#showTransactions} */
		String PAYOUT_EARNING 		= "/user/payout/earnings";
	}
	
	/**
	 * Define mapping of merchant
	 */
	interface Merchant {
		/** ::: {@link RegisterMerchantContoller#showRegisterForm} */
		String NEW_MERCHANT 			= "/user/merchant/register";

		
		interface Listing {
			/** ::: {@link NewListingPageController#showPage} */
			String NEW_LISTING 				= "/user/merchant/request/create-product";
			
			
			
			/** ::: {@link ListingPageController#showPage} */
			String LISTING 					= "/user/listings";
			
			/** ::: {@link PrivateListingPageController#showPage} */
			String PRIVATE_LISTING 	= "/user/listings/private";
			
			/** ::: {@link DeletedListingPageController#showPage} */
			String DELETED_LISTING = "/user/listings/deleted";
			
			
			
			
			/** ::: {@link ListingDetailPageController#showPage} */
			String LISTING_DETAIL 			= "/user/listings/{productId}";
			
			/** {@link GalleryPageController#showPage} */
			String LISTING_GALLERY 			= "/user/listings/photo/{productId}";
			
			/** {@link RemoveListingRequestPageController#showDeleteProductPage} */
			String LISTING_DELETE_CONFIRM 			= "/user/listings/delete-confirm";
			
		}
		
		interface Order {
			//-------------------
			// Display Order_Intent
			//-------------------
			
			/** {@link OrderIntentController#showGroupOrderOnProducts(Model, Account, String)} */
			String ORDER_INTENT 				= "/user/orders";
			
			/** {@link OrderIntentDetailPageController#showCustomerDetailOnProduct(Model, Account, Long, String)} */
			String ORDER_INTENT_DETAIL 			= "/user/orders/{productId}";
		
			

			//-------------------
			// Display Order_Transaction
			//-------------------
			
			
			/** {@link OrderTransactionController#showOrderTransactions(Model, Account, Integer)} */
			String ORDER_TRANSACTIONS 		= "/user/orders/transactions";
			
			/** {@link OrderTransactionDetailController#showOrderTransactionDetail(Model, Account, Long)} */
			String ORDER_TRANSACTION_DETAIL = "/user/orders/transactions/{_oid}";
		}
	}
	
	
	interface Errors {
			String MERCHANT_CREDENTIAL_FAIL = "/user/merchant/access-denied";
			String PRODUCT_NOT_FOUND = "/error/product-not-found";
	}
}
