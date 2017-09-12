package com.core.service.email;

import com.common.entity.account.Account;
import com.core.service.email.impl.ResetPasswordEmailServiceImpl;

public interface IResetPasswrodEmailService {

	/** {@link ResetPasswordEmailServiceImpl#sendPasswordResetEmail} */
	void sendPasswordResetEmail(final Account account);
	
	
}
