package com.core.service.account;

import com.common.entity.account.Account; 
import com.common.entity.account.VerificationToken;

public interface VerificationTokenService {

	/** {@link VerificationTokenServiceImpl#generateToken(Account)} */
	VerificationToken generateToken(Account account);
	
	
	/** {@link VerificationTokenServiceImpl#getByToken(String)} */
	VerificationToken getByToken(String token);

	
	/** {@link VerificationTokenServiceImpl#validateToken(Account, String)} */
	boolean validateToken(Account account, String token);
	
	
	/** {@link VerificationTokenServiceImpl#markAsUsed(VerificationToken)} */
	void markAsUsed(VerificationToken vToken);
	
	
	
	
}
