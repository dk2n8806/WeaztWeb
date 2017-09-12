package com.core.service.account.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.account.PasswordResetToken;
import com.core.dao.account.PasswordResetTokenDao;
import com.core.service.account.PasswordResetTokenService;

/**
 * 
 * @author dk2n_
 *
 */
@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	@Autowired private PasswordResetTokenDao passwordResetTokenDao;
	
	
	
/******************************************************************************
 * 
 * @see {@link PasswordResetTokenService#getByToken(String)}
 * @see {@link PasswordResetTokenDao#getByToken(String)}	
 */
	@Override
	public PasswordResetToken getByToken(String token) {
		if(token == null) {			return null;	}
		try {
			return passwordResetTokenDao.getByToken(token);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
	
	


/******************************************************************************
 * 
 * @see {@link PasswordResetTokenService#getByEmail(String)}
 * @see {@link PasswordResetTokenDao#getByEmail(Account)}
 * 
 */
/*	@Override
	public PasswordResetToken getByEmail(String email) {
		if(email == null) { return null;	}
		try {
			return passwordResetTokenDao.getByEmail(email);
		}catch(NoResultException e) {
			return null;
		}
	}

*/

	

	
/******************************************************************************
 * 
 * @see {@link PasswordResetTokenService#markAsHasUsed(PasswordResetToken)}
 * 
 */	
/*	@Override
	public void markAsHasUsed(PasswordResetToken passwordResetToken) {
		passwordResetToken.setUseable(true);
		passwordResetTokenDao.update(passwordResetToken);
	}
	
	
	*/
	
	
	



/**
 * Generate and save password_token by the account
 * {@link PasswordResetTokenService#generateToken}
 */
	@Override
	public PasswordResetToken generateToken(Account account) {
		PasswordResetToken passwordResetToken = PasswordResetToken.create(account);
		if(passwordResetToken != null) {
			passwordResetTokenDao.persist(passwordResetToken);
		}
		return passwordResetToken;
	}





	
	
/**
 * Retrieve a list of password_token by an account
 * {@link PasswordResetTokenService#getByAccount(Account)}
 */
	@Override
	public List<PasswordResetToken> getByAccount(Account account) {
		if(account == null) {
			return new ArrayList<>();
		}
		return passwordResetTokenDao.getByAccount(account);
	}
	
	
	
	
	
	
	
/**
 * Mark all token as hadUsed by account
 * 
 * {@link PasswordResetTokenService#markAsHasUsedByAccount(Account)}
 */
	@Override
	public void markAsHasUsedByAccount(Account account) {
		if(account != null) {
			passwordResetTokenDao.toggleUseableByAccount(true, account);
		}
	}

}
