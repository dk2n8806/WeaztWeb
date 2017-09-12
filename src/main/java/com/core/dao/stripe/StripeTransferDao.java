package com.core.dao.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeTransfer;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.stripe.impl.StripeTransferDaoImpl;

/***********************************************************************
 * 
 * @author dk
 *
 */
public interface StripeTransferDao extends GenericRepository<StripeTransfer, Long> {

	/** {@link StripeTransferDaoImpl#getByToken(String)} */
	StripeTransfer getByToken(String tokenId);

	/** {@link StripeTransferDaoImpl#getByStripeAccount(StripeAccount, Long)} */
	StripeTransfer getByStripeAccount(StripeAccount stripeAccount, Long transferId);

	/** {@link StripeTransferDaoImpl#getByMerchant(Merchant, Long)} */
	StripeTransfer getByMerchant(Merchant merchant, Long transferId);
	
	
	
	
	/** {@link StripeTransferDaoImpl#getList(DateInterval, Pageable)} */
	List<StripeTransfer> getList(DateInterval dateInterval, Pageable pageable);
	
	/** {@link StripeTransferDaoImpl#getListByStripeAccount(StripeAccount, DateInterval, Pageable)} */
	List<StripeTransfer> getListByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval, Pageable pageable);
	
	/** {@link StripeTransferDaoImpl#getListByMerchant(Merchant, DateInterval, Pageable)} */
	List<StripeTransfer> getListByMerchant(Merchant merchant, DateInterval dateInterval, Pageable pageable);
	
	
	
	
	/** {@link StripeTransferDaoImpl#countByStripeAccount(StripeAccount, DateInterval)} */
	long countByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval);
	
	/** {@link StripeTransferDaoImpl#countByMerchant(Merchant, DateInterval)} */
	long countByMerchant(Merchant merchant, DateInterval dateInterval);
	
	
}
