package com.core.service.account;

import java.util.List;

import com.common.entity.account.Account;
import com.common.entity.account.PasswordResetToken;

/**
 * */
public interface PasswordResetTokenService {

	/** {@link PasswordResetTokenServiceImpl#getByToken(String)} */
	PasswordResetToken getByToken(String token);
	
	/** {@link PasswordResetTokenServiceImpl#getByEmail(String)} */
	//PasswordResetToken getByEmail(String email);
	
	
	
	/** {@link PasswordResetTokenServiceImpl#markAsHasUsed(PasswordResetToken)} */
	//void markAsHasUsed(PasswordResetToken passwordResetToken);

	List<PasswordResetToken> getByAccount(Account account);
	void markAsHasUsedByAccount(Account account);
	
	/** {@link PasswordResetTokenServiceImpl#generateToken} */
	PasswordResetToken generateToken(Account account);
	
}
