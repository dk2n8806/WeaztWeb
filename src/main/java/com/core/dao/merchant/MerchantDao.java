package com.core.dao.merchant;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.TaxRate;
import com.common.type.MerchantStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.merchant.impl.MerchantDaoImpl;

public interface MerchantDao 
	extends GenericRepository<Merchant, Long>
{

	
	/** {@link MerchantDaoImpl#getByAccount(Account)} */
	Merchant getByAccount(Account account);

	
	/** {@link MerchantDaoImpl#getProfile(Merchant)} */
	MerchantProfile getProfile(Merchant merchant);
	
	
	/** {@link MerchantDaoImpl#getCommissionRate(Merchant)} */
	CommissionRate getCommissionRate(Merchant merchant);
	
	/** {@link MerchantDaoImpl#getTaxRate(Merchant)} */
	TaxRate getTaxRate(Merchant merchant);

	/** {@link MerchantDaoImpl#getAccount(Merchant)} */
	Account getAccount(Merchant merchant);
	
	
	
		
	/** {@link MerchantDaoImpl#updateStatus(Long, MerchantStatus)} */
	void updateStatus(Long merchantId, MerchantStatus status);


	
	/** {@link MerchantDaoImpl#getMerchants(MerchantStatus, DateInterval, Pageable)} */
	List<Merchant> getMerchants(MerchantStatus status, DateInterval dateInterval, Pageable pageable);

	
	
	/** {@link MerchantDaoImpl#countMerchants(MerchantStatus, DateInterval)} */
	long countMerchants(MerchantStatus status, DateInterval dateInterval);
	
	
}
