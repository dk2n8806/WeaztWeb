package com.core.service.review;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.review.ReviewDTO;
import com.common.dto.review.ReviewProductDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;

public interface ReviewDTOSerivice {

	/** Retrieve a list of reviews on merchant 
	 * {@link ReviewDTOServiceImpl#getByMerchant(Merchant, Pageable)} */
	List<ReviewProductDTO> 
	getByMerchant(Merchant merchant, Pageable page);
	
	
	/** Retrieve a list of reviews 
	 * {@link ReviewDTOServiceImpl#getByProduct(Product, Pageable)} */
	List<ReviewDTO> getByProduct(Product product, Pageable page);
}
