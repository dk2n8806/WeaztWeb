package com.core.service.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.review.ReviewDTO;
import com.common.dto.review.ReviewProductDTO;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.core.dao.review.dto.ReviewDTODao;


@Service
@Transactional
public class ReviewDTOServiceImpl implements ReviewDTOSerivice{

	@Autowired private ReviewDTODao reviewDTODao;

	
/**
 * Retrieve a list of reviews
 * 
 * @param  merchant (required)
 * @param page
 * @return a list of review
 * 
 * {@link ReviewDTOSerivice#getByMerchant(Merchant, Pageable)}
 */
	@Override
	public List<ReviewProductDTO> getByMerchant(Merchant merchant, Pageable page) {
		if(merchant == null) {
			return new ArrayList<>();
		}
		return reviewDTODao.getByMerchant(merchant, page);
	}

	
/**
 * Retrieve a list of reviews
 * 
 * @param product 		(required)
 * @param page
 * @return a list of reviews
 * 
 * {@link ReviewDTOSerivice#getByProduct(Product, Pageable)}
 */
	@Override
	public List<ReviewDTO> getByProduct(Product product, Pageable page) {
		if(product == null) {
			return new ArrayList<>();
		}
		return reviewDTODao.getByProduct(product, page);
	}
	

}
