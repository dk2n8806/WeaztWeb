package com.web.controller.page.listing;

import org.springframework.web.multipart.MultipartFile;

import com.common.entity.account.Account;
import com.common.exception.MerchantException;

public interface INewListingAdvise {

	/** {@link NewListingPageController#createNewListing} */
	String createNewListing(Account customer
									, MultipartFile _file, MultipartFile[] _files					// Item's images
									, Long _categoryId
									, String _title, String _description, Double _price
									, Double _shipping, String _shipType
									, Float _weight, String _mUnit
									, Float _width, Float _height, Float _depth, String _dUnit
									, Integer _shipment, Double _save, Integer _freq
									) 
									throws MerchantException;

	
}
