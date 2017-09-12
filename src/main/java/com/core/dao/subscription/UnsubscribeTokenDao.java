package com.core.dao.subscription;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.subscription.UnsubscribeToken;
import com.core.dao.generic.GenericRepository;
import com.core.dao.subscription.impl.UnsubscribeTokenDaoImpl;

public interface UnsubscribeTokenDao 
extends GenericRepository<UnsubscribeToken, Long>{


	/** {@link UnsubscribeTokenDaoImpl#getToken(Account, String)} */
	UnsubscribeToken getToken(Account customer, String token);
	
	/** {@link UnsubscribeTokenDaoImpl#getTokens(Account, Boolean, Pageable)} */
	List<UnsubscribeToken> getTokens(Account customer, Boolean isUseable, Pageable pageable);
	
	/** {@link UnsubscribeTokenDaoImpl#countTokens(Account, Boolean)} */
	long countTokens(Account customer, Boolean isUseable);
}
