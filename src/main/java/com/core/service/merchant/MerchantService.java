package com.core.service.merchant;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.address.Address;
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.TaxRate;
import com.common.entity.support.embeded.Phone;
import com.common.type.MerchantStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.merchant.impl.MerchantServiceImpl;

public interface MerchantService extends GenericService<Merchant, Long> {

	/** :::
	 * Create & save a new merchant entity
	 * {@link MerchantServiceImpl#save(Account, String, String, Phone, Address)}
	 * ::: */
	Merchant save(Account account, String companyName, String webstie, Phone phone, Address address);
	
	
	/** :::
	 * @return a merchant entity or null
	 * {@link MerchantServiceImpl#getByAccount(Account)}
	 * ::: */
	Merchant getByAccount(Account account);

	
	
	
	/** :::
	 * @return a list of merchants or empty list
	 * {@link MerchantServiceImpl#getMerchants(MerchantStatus, DateInterval, Pageable)}
	 * ::: */
	List<Merchant> getMerchants(MerchantStatus status, DateInterval dateInterval, Pageable pageable);
	
	

	/** :::
	 * @return total merchants or 0
	 * {@link MerchantServiceImpl#countMerchants(MerchantStatus, DateInterval)}
	 * ::: */
	long countMerchants(MerchantStatus status, DateInterval dateInterval);
	
	

	
	/** :::
	 * @return merchant profile or null
	 * {@link MerchantServiceImpl#getProfile(Merchant)} 
	 * ::: */
	MerchantProfile getProfile(Merchant merchant);
	
	
	/** :::
	 * @return commission rate or null
	 * {@link MerchantServiceImpl#getCommissionRate(Merchant)}
	 * ::: */
	CommissionRate getCommissionRate(Merchant merchant);
	
	
	/** :::
	 * @return tax_rate entity
	 * {@link MerchantServiceImpl#getTaxRate(Merchant)}
	 * ::: */
	TaxRate getTaxRate(Merchant merchant);

	
	/** :::
	 * @return Account entity of the merchant
	 * {@link MerchantServiceImpl#getAccount(Merchant)} 
	 * ::: */
	Account getAccount(Merchant merchant);
	
	
		
	/** :::
	 * Update status of the merchant
	 * {@link MerchantServiceImpl#updateStatus(Long, MerchantStatus)} 
	 * ::: */
	void updateStatus(Long merchantId, MerchantStatus status);


	
	

}
