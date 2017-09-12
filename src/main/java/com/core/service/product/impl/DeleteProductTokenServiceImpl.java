package com.core.service.product.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeleteProductToken;
import com.common.entity.product.Product;
import com.core.dao.product.DeleteProductTokenDao;
import com.core.service.AbstractTokenService;
import com.core.service.product.DeleteProductTokenService;

@Service
@Transactional
public class DeleteProductTokenServiceImpl implements DeleteProductTokenService {

	@Autowired private DeleteProductTokenDao tokenDao;
	@Autowired private AbstractTokenService abstractTokenService;
	
	@Override
	public DeleteProductToken findById(Long id) {
		return tokenDao.findById(id);
	}


	@Override
	public long getRowCount() {
		return tokenDao.getRowCount();
	}


	@Override
	public DeleteProductToken getReference(Long id) {
		return tokenDao.getReference(id);
	}

	
	
/****************************************************************
 * Create and save new delete_token on the product
 * 
 * {@link DeleteProductTokenService#save(Product)}
 */
	@Override
	public DeleteProductToken save(Product product) {
		DeleteProductToken token = DeleteProductToken.create(product);
		if(token != null) {
			tokenDao.persist(token);
		}
		return token;
	}

	
/***********************************************************************
 * Retrieve a delete_token by merchant
 * 
 * {@link DeleteProductTokenService#getByMerchant(Merchant, String)}
 */
	@Override
	public DeleteProductToken getByMerchant(Merchant merchant, String token) {
		if(merchant == null || token == null) {
			return null;
		}
		try {
			return tokenDao.getByMerchant(merchant, token);
		} catch(NoResultException e) {
			return null;
		}
	}

	
/*********************************************************
 * Update list of token
 * 
 * {@link DeleteProductTokenService#markAsUsed(List)}
 */
	@Override
	public void markAsUsed(List<DeleteProductToken> tokens) {
		if(tokens.size() > 0) {
			abstractTokenService.updateStatus(tokens, false);
		}
	}

	
	
	
/*******************************************************
 * Retrieve a list of delete_token by merchant
 * 
 * {@link DeleteProductTokenService#getByMerchant(Merchant, Boolean, Pageable)}
 */
	@Override
	public List<DeleteProductToken> getByMerchant(Merchant merchant,
			Boolean isUseable, Pageable pageable) {
		if(merchant == null) {
			return new ArrayList<DeleteProductToken>();
		}
		return tokenDao.getByMerchant(merchant, isUseable, pageable);
	}

	
/***********************************
 * Count totalCharge delete_token by merchant
 * 
 * {@link DeleteProductTokenService#countByMerchant(Merchant, Boolean)}
 */
	@Override
	public long countByMerchant(Merchant merchant, Boolean isUseable) {
		if(merchant == null) {
			return 0;
		}
		try {
			return tokenDao.countByMerchant(merchant, isUseable);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
/*****************************************
 * Mark the token as used
 * 
 * {@link DeleteProductTokenService#markAsUsed(DeleteProductToken)}
 */
	@Override
	public void markAsUsed(DeleteProductToken token) {
		if(token != null) {
			this.markAsUsed(Arrays.asList(token));
		}
	}

}
