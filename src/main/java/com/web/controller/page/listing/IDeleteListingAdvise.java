package com.web.controller.page.listing;


import com.common.entity.account.Account;
import com.common.exception.MerchantException;
import com.web.response.JsonResponse;

public interface IDeleteListingAdvise {

	
	/** {@link RemoveListingRequestPageController#deleteProductRequest(Account, Long, String, Integer)} */
	JsonResponse deleteProductRequest(Account account, Long _pid, String token, Integer quote) throws MerchantException;
}
