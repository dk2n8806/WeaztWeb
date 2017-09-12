package com.core.service.account.auth;

import javax.security.auth.login.AccountException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.common.entity.account.Account;
import com.common.exception.AccountNotFoundException;


/**
 * <h1>Account Authentication Service</h1>
 * 
 * @author dk2n_
 *
 */
public interface AccountAuthenticationService 
	extends UserDetailsService
{

	/** :::
	 * <p>Lookup an account by given username</p>
	 * {@link AccountAuthenticationServiceImpl#loadUserByUsername(String)} 
	 * */
	@Override Account loadUserByUsername(String username);
	
	

	/** :::
	 *	<p>Create & save a new account entity</p> 
	 * {@link AccountAuthenticationServiceImpl#save(Account, String)}
	 * ::: */
	Account save(Account account, String password) ;
	

	
	/** ::: 
	 * <p>Update account either username, email, or both</p>
	 * {@link AccountAuthenticationServiceImpl#updateCredentialInfo(Long, String, String)} 
	 * :::  */
	void updateCredentialInfo(Long accountId, String username
					, String email) throws AccountNotFoundException, AccountException;
	
	
	
	/** ::: 
	 * <p>Update account password</p>
	 * {@link AccountAuthenticationServiceImpl#changePassword(Long, String)} 
	 * :::  */
	void changePassword(Long accountId, String newPassword) throws AccountException ;
	
	
	
	/** :::
	 * <p>Verify an account password</p>
	 * {@link AccountAuthenticationServiceImpl#verifyPassword} 
	 * ::: */
	boolean verifyPassword(Long accountId, String oldPassword);
	
	
	
	/** :::
	 * <p>Verify an account email</p>
	 * {@link AccountAuthenticationServiceImpl#verifiedEmailAccount(Long)} 
	 * ::: */
	void verifiedEmailAccount(Long accountId)	throws AccountException;
	
	
	/** :::
	 * <p>Close the account</p>
	 *  {@link AccountAuthenticationServiceImpl#closeAccount(Long)} 
	 * ::: */
	void closeAccount(Long accountId)
						throws AccountException;
	
}
