package com.core.service.stripe;



import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.adapter.stripe.TransferAdapter;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeTransfer;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.stripe.impl.StripeTransferServiceImpl;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface StripeTransferService extends GenericService<StripeTransfer, Long>{

	/** {@link StripeTransferServiceImpl#create(StripeAccount, TransferAdapter)} */
	StripeTransfer create(StripeAccount stripeAccount, TransferAdapter transferAdapter);
	
	/** {@link StripeTransferServiceImpl#getByToken(String)} */
	StripeTransfer getByToken(String tokenId);

	/** {@link StripeTransferServiceImpl#getByStripeAccount(StripeAccount, Long)} */
	StripeTransfer getByStripeAccount(StripeAccount stripeAccount, Long transferId);
	
	/** {@link StripeTransferServiceImpl#getByMerchant(Merchant, Long)} */
	StripeTransfer getByMerchant(Merchant merchant, Long transferId);
	
	
	
	
	/** {@link StripeTransferServiceImpl#getList(DateInterval, Pageable)} */
	List<StripeTransfer> getList(DateInterval dateInterval, Pageable pageable);
	
	/** {@link StripeTransferServiceImpl#getListByStripeAccount(StripeAccount, Pageable)} */
	List<StripeTransfer> getListByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval, Pageable pageable);
	
	/** {@link StripeTransferServiceImpl#getListByMerchant(Merchant, Pageable)} */
	List<StripeTransfer> getListByMerchant(Merchant merchant, DateInterval dateInterval, Pageable pageable);
	
	
	
	
	/** {@link StripeTransferServiceImpl#countByStripeAccount(StripeAccount)} */
	long countByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval);
	
	/** {@link StripeTransferServiceImpl#countByMerchant(Merchant)} */
	long countByMerchant(Merchant merchant, DateInterval dateInterval);
	
	
	
}
