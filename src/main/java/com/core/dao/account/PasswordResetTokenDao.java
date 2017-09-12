package com.core.dao.account;

import java.util.List;

import com.common.entity.account.Account;
import com.common.entity.account.PasswordResetToken;
import com.core.dao.generic.GenericRepository;

/************************************************************************************
 * 
 * @author dk
 *
 */
public interface PasswordResetTokenDao 
extends GenericRepository<PasswordResetToken, Long>{

	/** {@link PasswordResetTokenDaoImpl#getByToken(String)} */
	PasswordResetToken getByToken(final String token);
	
	
	/** {@link PasswordResetTokenDaoImpl#getByEmail(String)} */
	//PasswordResetToken getByEmail(final String email);
	
	
	void toggleUseableByAccount(boolean useable, Account account);
	
	List<PasswordResetToken> getByAccount(Account account);
	
	
}
