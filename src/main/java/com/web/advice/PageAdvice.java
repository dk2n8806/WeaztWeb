

package com.web.advice;

import org.springframework.ui.Model;

import com.web.controller.page.admin.CategoryController;
import com.web.controller.page.admin.SubscriptionController;
import com.web.controller.page.admin.order.OrderBundleController;
import com.web.controller.page.admin.order.OrderIntentController;
import com.web.controller.page.admin.order.OrderTransactionController;
import com.web.controller.page.admin.payment.PaymentController;
import com.web.controller.page.admin.payout.PayoutController;
import com.web.controller.page.dashboard.BookmarkPageController;
import com.web.controller.page.listing.DeletedListingPageController;
import com.web.controller.page.listing.ListingPageController;
import com.web.controller.page.listing.PrivateListingPageController;
import com.web.controller.page.subscription.CurrentSubscritionRequestController;

public interface PageAdvice {
	
	interface Admin {
		
		/** {@link DashboardController#showMainAdminPage} */
		String DASHBOARD_PAGE = "/admin/display/dashboard";
		
		/** {@link MessageCenterController#showMainAdminPage} */
		String MESSAGE_PAGE = "/admin/display/messages";
		
		/** ::: {@link AccountController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_CUSTOMERS = "/admin/display/customers";
		
		
		/** ::: {@link CategoryController#showPage} */
		String DISPLAY_CATEGORIES = "/admin/display/categories";
		

//----
//
//----
		/** {@link ProductController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_PRODUCTS = "/admin/display/products";
		
		

//----
//
//----
		/** {@link SubscriptionController#showMainAdminPage(Model, Account, String)} */
		String Display_SUBSCRIPTIONS = "/admin/display/subscriptions";


		
		interface Order {
			/** ::: {@link OrderIntentController#showPage} */
			String ORER_INTENT_PAGE = "/admin/display/orderIntents";
			
			/** ::: {@link OrderBundleController#showPage} */
			String ORER_BUNDLE_PAGE = "/admin/display/orderBundles";
			
			/** ::: {@link OrderTransactionController#showPage} */
			String ORDER_TRANSACTION_PAGE = "/admin/display/orderTransaction";
		}
		
		interface Payment {
			/** ::: {@link PaymentController#showPage} */
			String PAYMENT_PAGE = "/admin/display/payments";
		}
		
		interface Payout {
			
			/** ::: {@link PayoutController#showPage} */
			String PAYOUT_PAGE = "/admin/display/payouts";
		}
//----
// Order
//----
		/** {@link OrderController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_ORDERS = "/admin/display/orders";
		
		
		/** {@link OrderRequestController#showMainAdminPage} */
		String ORDER_REQUEST_PAGE = "/admin/display/orderRequests";
		

//----
// Payment - Payout
//----
		/** {@link PaymentControler#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_PAYMENTS = "/admin/display/payments";
		
		/** {@link PaymentRequestController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_PAYMENT_REQUESTS = "/admin/display/paymentRequests";
		
		/** {@link PayoutController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_PAYOUTS = "/admin/display/payouts";
		
		

//----
//
//----
		/** {@link ShipmentController#showMainAdminPage(Model, Account, String)} */
		String DISPLAY_SHIPMENTS = "/admin/display/shipments";
		
		
	}
	
	interface Visit {
		/** {@link VisitMerchantProfileController#showMerchantProfile(Model, Long)} */
		String PROFILE_PAGE = "visit/userProfile";
	}
	
	interface Article {
		/** {@link AboutPageController#showAboutPage()} */
		String ABOUT_PAGE 			= "article/about";
		String TOS_TERM_PAGE 		= "article/tos";
		String PRIVACY_TERM_PAGE 	= "article/privacy";
		

		/** {@link ContactUsPageController#showPage()} */
		String CONTACT_US_PAGE 		= "article/contact";
	
		
		/** {@link PartnerIndexPageController#showMerchantIndextPage()} */
		String PARTNER_INDEX_PAGE = "article/partner/partnerIndex";
	}
	
	
	interface GlobalReponse {
		String VERIFICATION_RESPONSE = "global/account-verification-response";
	}
	
	interface Shop {
		
		/** {@link IndexPageController#index} */
		String INDEX = "index";
		
		/** {@link BrowsePageController#showIndexPage(Model, int, long)} */
		String SHOP = "shop";
		
		/** {@link ProductController#displayProduct(Model, long)} */
		String ITEM = "item";
		
		/** {@link SearchPageController#displaySearch(Model, String)} */
		String SEARCH = "search";
	}
	
	
	
	interface Checkout {
		/** {@link CheckoutPageController#showCheckoutPage(Model, Account, long)} */
		String CHECKOUT_PAGE 			= "user/checkout/checkout";
		
		String CHECKOUT_FAIL_PAGE 		= "user/checkout/failCheckout";
		String CHECKOUT_ERROR_PAGE 		= "user/checkout/errorCheckout";
		
		String CHECKOUT_CONFIRM 		= "user/checkout/confirmCheckout";
	}
	
	interface User {
		/** {@link DashboardPageController#showDashboardPage(Model, Account)} */
		String DASHBOARD 			= "user/modules/dashboard/dashboard";
		
		
		
		/** {@link SubscriptionController#showPage} */
		String SUBSCRIPTION 							= "user/modules/subscription/subscription";
		
		/** ::: {@link CurrentSubscritionRequestController#showPages} */
		String SUBSCRIPTION_REQUEST 		= "user/modules/subscription/subscriptionRequests";
		
		
		
		/** {@link SubscriptionDetailController#showSubscription(Model, Account, Long)} */
		String SUBSCRIPTION_DETAIL	= "user/modules/subscription/subscriptionDetail";
		
		/** {@link UnSubscribeRequestController#showUnsubscribePage(Model, Account, String)} */
		String UNSUBSCRIBE			= "user/modules/subscription/unsubscribe-request";

		
		/** {@link BookmarkPageController#showPage} */
		String BOOKMARK_PAGE = "user/modules/setting/bookmark";
		
		

		interface Shipment {
			/** {@link NextShipmentPageController#showPage} */
			String NEXT_SHIPMENTS = "user/modules/subscription/nextShipments";

			/** {@link HistoryShipmentPageController#showPage} */
			String HISTORY_SHIPMENTS = "user/modules/subscription/historyShipments";
		}
		
		
		/** {@link UnsubscriptionController#showPage(Model, Account, String, String, int)} */
		String UNSUBSCRIPTIONS = "user/modules/subscription/unsubscription";
		
		String SETTING 			= "user/modules/setting/setting";
		String TRANSACTION 		= "user/modules/transaction/transaction";
		
		/** {@link LoginPageController#showPage(Model)} */
		String LOGIN_FORM 			= "user/form/loginform";
		
		/** {@link RegisterPageController#showPage(Model)} */
		String REGISTER_FORM 		= "user/form/registerform";
		
		/** {@link ForgotPasswordController#showForgotPasswordPage()} */
		String FORGOT_PASSWORD_FORM = "user/form/forgotPasswordForm";
		
		/** {@link ForgotPasswordController#showResetPasswordPage(Model, String, String)} */
		String RESET_PASSWORD_FORM  = "user/form/resetPasswordForm";
		
	}
	
	
	interface Merchant {
		interface Listing {

			/** {@link NewListingPageController#showPage(Model, Account)} */
			String NEW_LISTING = "user/modules/listing/newListing";
			
			/** {@link ListingPageController#showListings} */
			String LISTING 			= "user/modules/listing/listing";
			
			/** {@link PrivateListingPageController#showListings} */
			String PRIVATE_LISTING = "user/modules/listing/privateListing";
			
			/** {@link DeletedListingPageController#showPage} */
			String HISTORY_LISTING = "user/modules/listing/historyListing";
			
			
			
			/** {@link ListingDetailPageController#showPage(Model, Account, Long)}*/
			String LISTING_DETAIL 	= "user/modules/listing/listingDetail";
			
			/** {@link GalleryPageController#showPage(Model, Account, Long)} */
			String LISTING_GALLERY = "user/modules/listing/listingGallery";
			
			/** {@link RemoveListingRequestPageController#showPage(Model, Account, String)} */
			String DELETE_LISTING = "user/modules/listing/delete-request";
			
			
		}
		
		interface Order {
			/** {@link OrderIntentController#showGroupOrderOnProducts(Model, Account, String)} */
			String ORDER_INTENT 					= "user/modules/order/orderIntent";
			
			
			
			/** {@link OrderIntentDetailPageController#showCustomerDetailOnProduct(Model, Account, Long, String)} */
			String ORDER_INTENT_CUSTOMER_DETAIL		= "user/modules/order/orderIntentCustomerDetail";
			
			
			
			
			/** {@link OrderTransactionController#showOrderTransactions(Model, Account, Integer)} */
			String ORDER_TRANSACTIONS 		= "user/modules/order/orderTransactions";
			
			/** {@link OrderTransactionDetailController#showOrderTransactionDetail(Model, Account, Long)} */
			String ORDER_TRANSACTION_DETAIL = "user/modules/order/orderTransactionDetails";
		}
		
		
		/** {@link RegisterMerchantContoller#showRegisterForm} */
		String NEW_MERCHANT_FORM = "user/form/merchantRegistrationForm";
		
		String MERCHANT_FORM_ERROR = "user/form/merchantRegistrationFormError";
		
	}
	
	
	interface Setting {
		/** {@link AccountSettingController#displayGeneralAccountSetting} */
		String ACCOUNT_SETTING 		= "user/modules/setting/account-setting";
		
		String PROFILE_SETTING 		= "user/modules/setting/profile-setting";
		
		/** {@link PasswordSettingController#showPasswordSettingPage} */
		String PASSWORD_SETTING 	= "user/modules/setting/password-setting";
		
		/** {@link MerchantSettingController#showSettingPage(Model, Account)} */
		String MERCHAT_SETTING 		= "user/modules/setting/merchant-setting";
		
		/** {@link ShippingSettingController#showPage(Model, Account)} */
		String SHIPPING_SETTING 	= "user/modules/setting/shipping-setting";
		
		String VERIFICATION_SETTING = "user/modules/setting/account-verification-setting";
	}
	
	
	interface Transaction {
		String PAYMENT 				= "user/modules/transaction/payment";
		String PAYMENT_TRANSACTION 	= "user/modules/transaction/paymentTransaction";
		String PAYOUT 				= "user/modules/transaction/payout";
		String PAYOUT_EARNING 		= "user/modules/transaction/payoutEarning";
		
	}
	
	interface Errors {
		String MERCHANT_EXCEPTION_ERROR = "errors/merchantExceptionError";
		String PRODUCT_NOT_FOUND_PAGE = "errors/productNotFound";
		String PAGE_404 = "errors/404";
	}
	
}
