package com.core.service.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.adapter.stripe.AccountAdapter;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeStatus;
import com.core.service.GenericService;
import com.core.service.stripe.impl.StripeAccountServiceImpl;

public interface StripeAccountService 
extends GenericService<StripeAccount, Long>{
	
	/** {@link StripeAccountServiceImpl#create(Merchant, AccountAdapter)} */
	StripeAccount create(Merchant merchant, AccountAdapter adapter);

	/** {@link StripeAccountServiceImpl#getByToken(String)} */
	StripeAccount getByToken(String tokenId);
	
	/** {@link StripeAccountServiceImpl#getDefaultByMerchant(Merchant)} */
	StripeAccount getDefaultByMerchant(Merchant merchant);
	
	/** {@link StripeAccountServiceImpl#getByMerchant(Merchant, Long)} */
	StripeAccount getByMerchant(Merchant merchant, Long stripeId);
	
	
	
	/** {@link StripeAccountServiceImpl#updateStatus(StripeAccount, StripeStatus)} */
	@Deprecated
	void updateStatus(StripeAccount stripe, StripeStatus status);
	
	/** {@link StripeAccountServiceImpl#updateStatus(List, StripeStatus)} */
	@Deprecated
	void updateStatus(List<StripeAccount> stripes, StripeStatus status);
	
	
	
	/** {@link StripeAccountServiceImpl#markAsActive(StripeAccount)} */
	void markAsActive(StripeAccount stripe);
	
	/** {@link StripeAccountServiceImpl#markAsDefault(StripeAccount)} */
	void markAsDefault(StripeAccount stripe);
	
	/** {@link StripeAccountServiceImpl#markAsDelete(StripeAccount)} */
	void markAsDelete(StripeAccount stripe);
	

	
	/** {@link StripeAccountServiceImpl#getByMerchant(Merchant, StripeStatus, Pageable)} */
	List<StripeAccount> getByMerchant(Merchant merchant, StripeStatus status, Pageable pageable);
	
	/** {@link StripeAccountServiceImpl#getList(StripeStatus, Pageable)} */
	List<StripeAccount> getList(StripeStatus status, Pageable pageable);
	
	
	
	
	
	/** {@link StripeAccountServiceImpl#countByMerchant(Merchant, StripeStatus)} */
	long countByMerchant(Merchant merchant, StripeStatus status);
	
	/** {@link StripeAccountServiceImpl#count(StripeStatus)} */
	long count(StripeStatus status);
}
