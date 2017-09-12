package com.core.service.account.impl;


import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.account.VerificationToken;
import com.core.dao.account.VerificationTokenDao;
import com.core.service.account.VerificationTokenService;


/**
 * 
 * @author dk2n_
 *
 */
@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired private VerificationTokenDao verificationTokenDao;
	

	
/************************************
 * Retrieve a verification token entity by an input token
 * 
 * @service {@link VerificationTokenService#getByToken(String)}
 * @dao {@link VerificationTokenDao#getByToken(String)}
 * 
 */
	@Override
	public VerificationToken getByToken(String token) {
		if(token == null || token.equals("")) {
			return null;
		}
		try {
			return verificationTokenDao.getByToken(token);
		} catch(NoResultException e) {
			return null;
		}
	}



	
/**************************************
 * 
 * @sevice {@link VerificationTokenService#generateToken(Account)}
 * 
 */
	@Override
	public VerificationToken generateToken(Account account) {
		try {
			VerificationToken vToken = VerificationToken.create(account);
			if(vToken != null) {
				verificationTokenDao.persist(vToken);
			}
			return vToken;
		} catch(DataIntegrityViolationException e) {
			return null;
		}
	}




/***********************************
 * 
 * @service {@link VerificationTokenService#validateToken(Account, String)}
 * 
 */
	@Override
	public boolean validateToken(Account account, String token) {
		boolean flag = false;
		VerificationToken vToken = this.getByToken(token);
		if(vToken != null) {
			if(vToken.getAccount().getId().equals(account.getId())) {
				flag = true;
			}
		}
		return flag;
	}




/***********************************
 * {@link VerificationTokenService#markAsUsed(VerificationToken)}
 */
	@Override
	public void markAsUsed(VerificationToken vToken) {
		vToken.setUseable(false);
		verificationTokenDao.update(vToken);
	}



	

}
