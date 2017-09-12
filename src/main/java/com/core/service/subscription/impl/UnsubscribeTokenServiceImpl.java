package com.core.service.subscription.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.UnsubscribeToken;
import com.core.dao.subscription.UnsubscribeTokenDao;
import com.core.service.AbstractTokenService;
import com.core.service.subscription.UnsubscribeTokenService;

@Service
@Transactional
public class UnsubscribeTokenServiceImpl implements UnsubscribeTokenService {


	@Autowired private UnsubscribeTokenDao tokenDao;
	@Autowired private AbstractTokenService abstractTokenService;

	@Override
	public UnsubscribeToken findById(Long id) {
		return tokenDao.findById(id);
	}


	@Override
	public long getRowCount() {
		return tokenDao.getRowCount();
	}


	@Override
	public UnsubscribeToken getReference(Long id) {
		return tokenDao.getReference(id);
	}

	
/******************
 * Create & persit new unsubscribe_token
 * 
 * {@link UnsubscribeTokenService#save(Subscription)}
 */
	@Override
	public UnsubscribeToken save(Subscription subscription) {
		UnsubscribeToken token = UnsubscribeToken.create(subscription);
		if(token != null) {
			tokenDao.persist(token);
		}
		return token;
	}
	
	
/**********************************************
 * Retrieve a list of unsubscribe_token by customer
 * 
 * {@link UnsubscribeTokenService#getByCustomer(Account, boolean, Pageable)}
 */
	@Override
	public List<UnsubscribeToken> getByCustomer(Account customer,
			boolean isUseable, Pageable pageable) {
		if(customer == null) {
			return new ArrayList<UnsubscribeToken>();
		}
		return tokenDao.getTokens(customer, isUseable, pageable);
	}

	
/**********************************************
 * Count totalCharge unsubscribe_token by customer
 * 
 * {@link UnsubscribeTokenService#countByCustomer(Account, boolean)}
 */
	@Override
	public long countByCustomer(Account customer, boolean isUseable) {
		if(customer == null) {
			return 0;
		}
		try {
			return tokenDao.countTokens(customer, isUseable);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	

/**********************************************
 * Retrieve a unsubscribe_token by customer
 * 
 * {@link UnsubscribeTokenService#getByCustomer(Account, String)}
 */
	@Override
	public UnsubscribeToken getByCustomer(Account customer, String token) {
		if(customer == null || token == null) {
			return null;
		}
		try {
			return tokenDao.getToken(customer, token);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
/***************************************
 * Update unsubscribe_token
 * 
 * {@link UnsubscribeTokenService#markAsUsed(List)}
 */
	@Override
	public void markAsUsed(List<UnsubscribeToken> tokens) {
		if(tokens.size() > 0) {
			abstractTokenService.updateStatus(tokens, false);
		}
	}
}
