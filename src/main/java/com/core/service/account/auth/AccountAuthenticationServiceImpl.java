package com.core.service.account.auth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.security.auth.login.AccountException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.exception.AccountNotFoundException;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.AccountUtil;
import com.core.dao.account.AccountDao;
import com.core.service.account.AccountService;
import com.core.service.merchant.MerchantService;

/**
 * <h1>Account Authentication Service</h1>
 * <p></p>
 * 
 * 
 * @author dk2n_
 *
 */
@Service
@Transactional
public class AccountAuthenticationServiceImpl implements AccountAuthenticationService {

	@Autowired private AccountService accountService;
	@Autowired private AccountDao accountDao;
	@Autowired private MerchantService merchantService;
	
	
	private static final SecureRandom RANDOM;
	
	static {
	        try        {
	        	RANDOM = SecureRandom.getInstance("SHA1PRNG");
	        } catch(NoSuchAlgorithmException e){
	            throw new IllegalStateException(e);
	        }
	    }
	  
	  private static final int HASHING_ROUNDS = 10;
	
	  
	  
	  private static final Logger logger = LogManager.getLogger();
	  
	  
	/** :::
	 * <h1>Load an account by given username</h1>
	 * 
	 * <p>Retrieve the account by given username. If the account is validated, 
	 * grant role and authority level to the account accordingly.</p>
	 * 
	 * {@link AccountAuthenticationService#loadUserByUsername(String)}
	 * ::: */
	@Override
	public Account loadUserByUsername(String username) {
		Account account = accountService.getByEmail(username);
		
		// TODO need better handling if the account is deactivate
		if(account == null || account.getStatus().equals(AccountStatus.DEACTIVE))  {
			throw new AccountNotFoundException();
		} 
		// ::: Set the authority of the account
		account.setAuthorities(getGrantedAuthorities(getPrincipalRoles(account.getRole())));
		
		// ::: Bind the merchant to the account
		
		if(account.getRole().equals(Role.MERCHANT)) {
			account.setMerchant( merchantService.getByAccount(account));
		}
		return account;
	}
	
	

	/** :::
	 * <p>Render authority level of the account</p>
	 * ::: */
	private List<String> getPrincipalRoles(Role role) 
	{
		List<String> roles = new ArrayList<String>();
		if(role == Role.ADMIN || role == Role.CUSTOMER || role == Role.MERCHANT) {
			roles.add("ROLE_USER");
				
		}
		return roles;
	}

	
	/** :::
	 * <p>Provide authorities level to the login account</p>
	 * ::: */
	private static Set<GrantedAuthority> getGrantedAuthorities(List<String> roles) 
	{
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

	
	
	
	
	

	/** :::
	 * <h1>Create & save a new account entity</h1>
	 * <p>Validate the client credential info and create an account for the client</p>
	 * 
	 * {@link AccountAuthenticationService#save(Account, String)}
	 * ::: */
	@Override
	public Account save(Account account, String password) {
		
		try {
			if(!AccountUtil.isSecuredPasswordFormat(password)) {
				throw new IllegalArgumentException("Invalid account password format");
			}
			
			String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
			account = Account.createEntityInstance(account.getUsername(), account.getEmail(), BCrypt.hashpw(password, salt).getBytes());
			
			if(account == null) {
				throw new IllegalArgumentException("Unable to create an account for the client");
			}
			
			// ::: Persist the account to table
			// ::: gain authority level to the account
			// ::: and save to secure context session
			accountDao.persist(account);
			Set<GrantedAuthority> authorities = getGrantedAuthorities(getPrincipalRoles(account.getRole()));
			Authentication auth = new UsernamePasswordAuthenticationToken(account, password, authorities);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			return account;
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}

	
	
	
	
	
	
	/** :::
	 * <h1>Update the account credential info</h1>
	 * 
	 * {@link AccountAuthenticationService#updateCredentialInfo(Account, String, String)}
	 *  :::  */
	@SuppressWarnings("deprecation")
	@Override
	public void updateCredentialInfo(
			Long accountId, String username, String email) throws AccountNotFoundException, AccountException 
	{
		try {
			Account entity = accountService.findById(accountId);
			if(entity.getStatus().equals(AccountStatus.DEACTIVE)) 
				throw new AccountException("Invalid account stage");
			
			boolean update = false;
			if(AccountUtil.isUsernameFormat(username)) {
				entity.setUsername(username);
			}
			if(AccountUtil.isEmailFormat(email)) {
				// TODO may need to the customer to update the email again.
				entity.setEmail(email);
				entity.setHasVerified(false);
			}
			if(update) {
				accountDao.update(entity);
			}
			
		} catch(IllegalArgumentException e)  {
			throw new AccountNotFoundException();
		}
	}

	
	
	
	
	/** :::
	 * <h1>Update account password</h1>
	 * 
	 * {@link AccountAuthenticationService#changePassword(Account, String)}
	 * :::  */
	@Override
	public void changePassword(Long accountId, String newPassword) throws AccountException {
		if(!AccountUtil.isSecuredPasswordFormat(newPassword)) {
			throw new AccountException();
		}

		Account entity = accountService.findById(accountId);
		if(entity.getStatus().equals(AccountStatus.DEACTIVE)) 
			throw new AccountException("Invalid account stage");
		
		String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
		entity.setHashedPassword(BCrypt.hashpw(newPassword, salt).getBytes());
		accountDao.update(entity);
	}

	
	
	
	/** :::
	 * <h1>Verify password</h1>
	 * 
	 * {@link AccountAuthenticationService#verifyPassword}
	 * ::: */
	@Override
	public boolean verifyPassword(Long accountId, String oldPassword) {
		Account account = accountService.findById(accountId);
		return BCrypt.checkpw(oldPassword, new String(account.getHashedPassword()));
	}

	
	/** :::
	 * <h1>Verify email</h1>
	 * Mark the account has change the email
	 * 
	 * {@link AccountAuthenticationService#verifiedEmailAccount(Long)}
	 * ::: */
	@Override
	public void verifiedEmailAccount(Long accountId) throws AccountException {
		Account customer = accountService.findById(accountId);
		if(customer != null) {
			customer.setHasVerified(true);
			accountDao.update(customer);
		} else {
			throw new AccountException();
		}
	}

	
	/** :::
	 * <h1>Close the account</h1>
	 * <p>Close the account will deactivate the account</p>
	 * {@link AccountAuthenticationService#closeAccount(Long)}
	 * ::: */
	@Override
	public void closeAccount(Long accountId) throws AccountException {
		Account account = accountService.findById(accountId);
		if(account != null) {
			account.setStatus(AccountStatus.DEACTIVE);
			accountDao.update(account);
		} else {
			throw new AccountException();
		}
	}


}
