package com.core.service.email;

import com.common.entity.account.Account;
import com.core.service.email.impl.EmailVerificationServiceImpl;


/******************************************************************************
 * Send an email to an account to verify the account email address
 * 
 * @author dk
 *
 */
public interface IEmailVerificationService {

	
	/** {@link EmailVerificationServiceImpl#sendVerfiicationEmail(Account, String)} */
	void sendVerfiicationEmail(final Account account);
}
