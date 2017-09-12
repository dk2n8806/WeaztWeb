package com.core.dao.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeStatus;
import com.core.dao.generic.GenericRepository;
import com.core.dao.stripe.impl.StripeAccountDaoImpl;

/***********************************************************************
 * 
 * @author dk
 *
 */
public interface StripeAccountDao extends GenericRepository<StripeAccount, Long>{

	/** {@link StripeAccountDaoImpl#getByToken(String)} */
	StripeAccount getByToken(String tokenId);
	
	/** {@link StripeAccountDaoImpl#getByMerchant(Merchant, Long)} */
	StripeAccount getByMerchant(Merchant merchant, Long stripeId);
	
	

	
	/** {@link StripeAccountDaoImpl#updateStatus(List, StripeStatus)} */
	void updateStatus(List<StripeAccount> stripe, StripeStatus status);
	
	
	/** {@link StripeAccountDaoImpl#getListByMerchant(Merchant, StripeStatus, Pageable)} */
	List<StripeAccount> getListByMerchant(Merchant merchant, StripeStatus status, Pageable pageable);
	
	/** {@link StripeAccountDaoImpl#getList(StripeStatus, Pageable)} */
	List<StripeAccount> getList(StripeStatus status, Pageable pageable);
	
	
	
	
	
	
	/** {@link StripeAccountDaoImpl#countByMerchant(Merchant, StripeStatus)} */
	long countByMerchant(Merchant merchant, StripeStatus status);
	
	/** {@link StripeAccountDaoImpl#count(StripeStatus)} */
	long count(StripeStatus status);
}
