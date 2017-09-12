package com.core.dao.account;

import com.common.entity.account.VerificationToken;
import com.core.dao.generic.GenericRepository;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface VerificationTokenDao 
extends GenericRepository<VerificationToken, Long>{

	
	/** {@link VerificationTokenDaoImpl#getByToken(String)} */
	VerificationToken getByToken(String token);
	
	
}
