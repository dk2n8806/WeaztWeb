package com.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.AbstractPersistenceToken;
import com.core.dao.AbstractTokenDao;

@Service
@Transactional
public class AbstractTokenServiceImpl implements AbstractTokenService {

	@Autowired private AbstractTokenDao tokenDao;
	
	

/*********************************************
 * Update status of a token
 * 
 * {@link AbstractTokenService#updateStatus(AbstractPersistenceToken, boolean)}
 */
	@Override
	public void updateStatus(AbstractPersistenceToken token, boolean isUseable) {
		if(token != null) {
			List<AbstractPersistenceToken> tokens = new ArrayList<AbstractPersistenceToken>();
			tokens.add(token);
			this.updateStatus(tokens, isUseable);
		}
	}

/*********************************************
 * Update a list of tokens
 * 
 * {@link AbstractTokenService#updateStatus(List, boolean)}
 */
	@Override
	public void updateStatus(List<? extends AbstractPersistenceToken> tokens, boolean isUseable) {
		if(tokens.size() > 0) {
			tokenDao.updateStatus(tokens, isUseable);
			for(AbstractPersistenceToken token : tokens) {
				token.setUseable(isUseable);
			}
		}
	}
	
	
/****************************
 * get by id
 * 
 * {@link AbstractTokenService#getById(Long)}
 */
	@Override
	public AbstractPersistenceToken getById(Long tokenId) {
		if(tokenId == null) {
			return null;
		}
		try {
			return tokenDao.getById(tokenId);
		} catch(NoResultException e){
			return null;
		}
	}
	
	
}
