package com.core.service.subscription;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.UnsubscribeToken;
import com.core.service.GenericService;

public interface UnsubscribeTokenService extends GenericService<UnsubscribeToken, Long>{

	/** {@link UnsubscribeTokenServiceImpl#save(Subscription)} */
	UnsubscribeToken save(Subscription subscription);
	
	/** {@link UnsubscribeTokenServiceImpl#getToken(Account, String)} */
	UnsubscribeToken getByCustomer(Account customer, String token);
	
	/** {@link UnsubscribeTokenServiceImpl#markAsUsed(List)} */
	void markAsUsed(List<UnsubscribeToken> tokens);
	
	
	/** {@link UnsubscribeTokenServiceImpl#getTokens(Account, boolean, Pageable)} */
	List<UnsubscribeToken> getByCustomer(Account customer, boolean isUseable, Pageable pageable);
	
	/** {@link UnsubscribeTokenServiceImpl#countTokens(Account, boolean)} */
	long countByCustomer(Account customer, boolean isUseable);
}
