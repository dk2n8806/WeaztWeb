package com.web.controller.page.listing;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;


public interface IListingDetailAdvise {



	/** Request to activate a listing  
	 * {@link ListingDetailPageController#activeListing(Account, Long)} */
	JsonResponse activeListing(Account customer, Long _pid) 
									throws MerchantException;

	/** Request to deactivate a listing
	 * {@link ListingDetailPageController#deactiveListing(Account, Long)} */
	JsonResponse deactiveListing(Account customer, Long _pid) 
									throws MerchantException;

	/** Request to delete a listing 
	 * {@link ListingDetailPageController#deleteListing} */
	JsonResponse deleteListing(Account customer, Long _pid) 
									throws MerchantException;

	

	// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// Update Product Info
	// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

		

	/** ::: {@link ListingDetailPageController#updateTitle} */
	JsonResponse updateTitle(Account customer, Long productId, String title);
	
	/** ::: {@link ListingDetailPageController#updateDescription} */
	JsonResponse updateDescription(Account customer, Long productId, String description);
	
	/** {@link ListingDetailPageController#updateImage} */
	//@Deprecated	JsonResponse updateImage(Account customer, Long productId, MultipartFile image);
	
	/** {@link ListingDetailPageController#updateCategory} */
	///@Deprecated	JsonResponse updateCategory(Account customer, Long productId, Long categoryId);
	
	/** {@link ListingDetailPageController#updatePrice} */
	JsonResponse updatePrice(Account customer, Long productId, double price);
	
	/** ::: Provide a new subscription_rate on  the item 
	 * {@link ListingDetailPageController#updateSubscriptionRate} */
	JsonResponse updateSubscriptionRate(Account customer, Long productId, double rate);
	
	/** ::: {@link ListingDetailPageController#updateSubscriptionShipment} */
	JsonResponse updateSubscriptionShipment(Account customer
										, Long productId, int numberOfShipment);
	
	/** ::: {@link ListingDetailPageController#updateFrequency} */
	JsonResponse updateFrequency(Account customer, Long productId, int frequency);
	
	
	/** ::: {@link ListingDetailPageController#updateShipping}*/
	JsonResponse updateShipping(Account customer, Long productId, String type, Double cost);
	
	
	/** ::: {@link ListingDetailPageController#updateParcel} */
	JsonResponse updateParcel(Account customer, Long productId
										, Float weight, String mUnit
										, Float length, Float width, Float height, String dUnit);
	
}
