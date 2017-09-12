package com.core.dao.review.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.review.ReviewDTO;
import com.common.dto.review.ReviewProductDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.review.Review;
import com.core.dao.generic.GenericRepository;

public interface ReviewDTODao 
extends GenericRepository<Review, Long>{


	/** Retrieve a list of reviews on merchant
	 * {@link ReviewDTODaoImpl#getByMerchant(Merchant, Pageable)} */
	List<ReviewProductDTO> getByMerchant(Merchant merchant, Pageable page);
	
	
	/** Retrieve a list of reviews on product
	 * {@link ReviewDTODaoImpl#getByProduct(Product, Pageable)} */
	List<ReviewDTO> getByProduct(Product product, Pageable page);
}
