package com.core.service.email;

import com.common.entity.account.Account;
import com.core.service.email.impl.WelcomeEmailServiceImpl;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface IWelcomeEmailService {

	/** {@link WelcomeEmailServiceImpl#sendWelcomeEmail(Account)} */
	void sendWelcomeEmail(final Account account);
	
}
