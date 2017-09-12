package com.web.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.controller.page.listing.ListingDetailPageController;
import com.web.controller.page.merchant.RegisterMerchantContoller;
import com.web.controller.page.subscription.SubscriptionDetailController;


public interface UriActionRequestMapping {

	interface User {
		String REQUEST_PASSWORD_TOKEN = "/request-password-token";
		String REQUEST_RESET_PASSWORD = "/request-reset-password";
		
		
		
		
		interface Checkout {

			//----------------------------------------
			//  Checkout page
			//----------------------------------------
			
			/** {@link CheckoutPageController#processCheckout(Account, long, int, int)} */
			String CHECKOUT_REQUEST = "/user/request/checkout-product";
			
			/** {@link CheckoutPageController#updateCheckoutAddress(Account, String, String, String, String, String)} */
			String CHECKOUT_UPDATE_SHIPPING = "/user/checkout/update-shipping";
			
			/** {@link CheckoutPageController#updatePaymentInfo(Account, String)} */
			String CHECKOUT_UPDATE_PAYMENT = "/user/checkout/update-payment";
			
			String CHECKOUT_UPDATE_PROMOTION = "/user/checkout/update-promotion";
		}
		
		
		interface Subscription {
			
			//----------------------------------------
			//  Subscription page
			//----------------------------------------
			
			//String TOGGLE_FAVORITE	= "/user/subscription/favorite/toggle";
			//String TOGGLE_STAR 		= "/user/subscription/star/toggle";
			
			//String REQUEST_UPDATE_SUBSCRIBER_NAME = "/user/subscription/update/subscriber-name";
			//String REQUEST_UPDATE_SUBSCRIBER_ADDRESS = "/user/subscription/update/subscriber-address";
	
			
			
			String TOGGLE_RATING_REQUEST = "/user/subscription/toggle-rating";
			
			
			/** ::: Request to update delivery in different time
			 *  {@link SubscriptionDetailController#updateDeliverFrequency} */
			String REQUEST_UPDATE_SUBSCRIPTION_DELIVER_FREQ = "/user/subscription/update/deliver-freq";
		
	
			/** ::: request to cancel a subscription
			 * {@link SubscriptionDetailController#generateUnsubscribeToken} */
			String UNSUBSCRIBE_REQUEST = "/user/subscription/generate-unsubscribe-token";			
			
			
			//----------------------------------------
			//  Unsubscribe page
			//----------------------------------------
			
			/** {@link UnSubscribeRequestController#unsubscribeRequest(Account, Long, String)} */
			String CONFIRM_UNSUBSCRIB = "/user/request/unsubscribe/subscription";
			
			
		}
		
		interface Review {
			/** ::: {@link SubscriptionDetailController#writeReview} */
			String WRITE_REVIEW = "/user/subscription/write-new-review";
		}
		
		interface Merchant {
			/*TODO*/String VISIT_MERCHANT = "/user/merchant/visit";
		}
	}
	
	
	
	
	interface Merchant {
		/** ::: {@link RegisterMerchantContoller#createNewMerchant} */
		String CREATE_NEW_MERCHANT = "/user/merchant/new-apply";
		
		
		interface Order {
			String ACCEPT_PRODUCT_ORDER 		= "/user/orders/merchant/request-order-product";
			String REACTIVE_PRODUCT_ORDER 		= "/user/orders/merchant/request-reactive-product";
			String REACTIVE_ALL_PRODUCT_ORDER 	= "/user/orders/merchant/request-reactive-holding-orders";
			
			
			
			String HOLD_CUSTOMER_ORDER 			= "/user/orders/hold/account";
			String DEQUEUE_CUSTOMER_ORDER 		= "/user/orders/dequeue/account";
			String ACCEPT_CUSTOMER_ORDER 		= "/user/orders/accept/account";
			String ACCEPT_ALL_CUSTOMER_ORDERS	= "/user/orders/accept/all-customers";
			
			
			interface Shipment {
				
				/** {@link OrderTransactionDetailController#downloadLabel(Account, Long, HttpServletResponse)} */
				String DOWNLOAD_LABEL = "/download-label";
			}
		}
		
		interface Listing {
			
			
			/** {@link ListingPageController#createNewListing(Account, HttpServletRequest)} */
			String CREATE_LISTING = "/user/create/listing";
			
			
	//------------------
	// Listing Detail Page
	//------------------
	
			/** ::: {@link ListingDetailPageController#activeListing(Account, Long)} */
			String ACTIVE_LISTING 	= "/user/listing/active-request";
			
			/** ::: {@link ListingDetailPageController#deactiveListing(Account, Long)} */
			String DEACTIVE_LISTING = "/user/listing/deactive-request";
			
			/** ::: {@link ListingDetailPageController#deleteListing(Account, Long)} */
			String DELETE_LISTING 	= "/user/listing/delete-request";

			
			
	//-----------
	// Update Listing
	//-----------
			/** {@link ListingDetailPageController#updateSubscriptionInfo} */
			String UPDATE_SUBSCRIPTION_INFO = "/user/listing/update-subscription-info";
			
			/** ::: {@link ListingDetailPageController#updateParcel} */
			String UPDATE_PARCEL			= "/user/listing/update-parcel";
			
			/** ::: {@link ListingDetailPageController#updateShipping} */
			String UPDATE_SHIPPING = "/user/listing/update-shipping";
			
			
			/** ::: {@link ListingDetailPageController#updateTitle} */
			String UPDATE_LISTING_TITLE 		= "/user/listing/update-title";
			
			/** ::: {@link ListingDetailPageController#updateDescription} */
			String UPDATE_LISTING_DESCRIPTION 	= "/user/listing/update-description";

			/** ::: {@link ListingDetailPageController#updateImage} */
			//String UPDATE_LISTING_IMAGE 		= "/user/listing/update-image";

			/** {@link ListingDetailPageController#updateCategory} */
			//String UPDATE_LISTING_CATEGORY 		= "/user/listing/update-category";

			/** ::: {@link ListingDetailPageController#updatePrice} */
			String UPDATE_LISTING_PRICE 		= "/user/listing/update-price";

			/** ::: {@link ListingDetailPageController#updateSubscriptionRate} */
			String UPDATE_LISTING_RATE 			= "/user/listing/update-rate";

			/** ::: {@link ListingDetailPageController#updateSubscriptionShipment} */
			String UPDATE_LISTING_SHIPMENT 		= "/user/listing/update-shipment";

			/** ::: {@link ListingDetailPageController#updateFrequency} */
			String UPDATE_LISTING_FREQUENCY 	= "/user/listing/update-frequency";
			
			
			
	//------------------
	// Delete-Listing Page
	//------------------
	
			/** {@link RemoveListingRequestPageController#deleteProductRequest(Account, Long, String, Integer)} */
			String DELETE_CONFIRM 	= "/user/listing/delete-confirm";
			
			
			
	//---------------------
	// Product Gallery Page
	//-----------------
			String CHANGE_MAIN_PHOTO = "/user/listing/change-main-photo";
			
			
			/** {@link GalleryPageController#uploadImage} */
			String UPLOAD_PHOTO = "/user/listing/upload-gallery-photo";
			
			/** {@link GalleryPageController#deleteGallery} */
			String DELETE_PHOTO = "/user/listing/delete-gallery-photo";
		}
		
	}
	
	
	interface Setting {
// ###
//  Account action
// ###
		/** {@link AccountSettingController#updateGeneralAccount} */
		String UPDATE_ACCOUNT_INFO = "/user/update-account";

		/** {@link AccountSettingController#updatePhoto} */
		String UPDATE_PROFILE_IMAGE = "/user/update-profile-image";

		
// ###
//  Account_Shipping Setting Page
// ###

		/** {@link ShippingSettingController#createShippingInfo} */
		String CREATE_SHIPPING_INFO	= "/user/create-shipping";
		
		/** {@link ShippingSettingController#setPrimary} */
		String SET_PRIMARY_SHIPPING = "/user/make-primary";
		
		/** {@link ShippingSettingController#delete} */
		String DELETE_SHIPPING		= "/user/delete-shipping";
		

// ###
//  Account_profile Setting Page
// ###
		/** {@link ProfileSettingController#updateProfile} */
		String UPDATE_PROFILE = "/user/update-profile";
		
		
// ###
// Account_Password Setting page	
// ###
		/** ::: {@link PasswordSettingController#updatePassword(Account, String, String, String)} */
		String UPDATE_PASSWORD = "/user/update-account-password";
		

// ###
// Merchant Setting page	
// ###
		/** {@link MerchantSettingController#updateContact} */
		String UPDATE_MERCHANT_INFO = "/user/update-merchant-account-info";	
		
		/** {@link MerchantSettingController#updateTax} */
		String UPDATE_MERCHANT_TAX = "/user/update-merchant-tax";
		
		

// ###
// Verification Setting page	
// ###
		/** {@link AccountVerificationController#requestNewVerificationToken(Account)} */
		String VERIFIED_ACCOUNT_REQUEST = "/user/request-verification-token";
	}
	
	
	
	interface Transaction {
		String CREATE_NEW_PAYMENT = "/user/create-new-payment";
		String DELETE_A_PAYMENT = "/user/delete-a-payment";
		String SET_DEFAULT_A_PAYMENT = "/user/set-default-a-payment";
		
		
		String CREATE_NEW_PAYOUT = "/user/create-new-payout";
		String DELETE_A_PAYOUT = "/user/delete-a-payout";
		String SET_DEFAULT_A_PAYOUT = "/user/set-default-a-payout";
		
	}
}
