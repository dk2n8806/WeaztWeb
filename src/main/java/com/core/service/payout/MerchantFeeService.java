package com.core.service.payout;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.MerchantFee;
import com.common.type.FeeStatus;
import com.common.util.date.DateInterval;
import com.core.service.payout.impl.MerchantFeeServiceImpl;

public interface MerchantFeeService {

	/** {@link MerchantFeeServiceImpl#save(MerchantFee)} */
	MerchantFee save(MerchantFee fee);
	
	List<MerchantFee> getByMerchant(Merchant merchant, FeeStatus status, DateInterval dateInterval, Pageable pageable);
	
	long countByMerchant(Merchant merchant, FeeStatus status, DateInterval dateInterval);
	
	int getTotalFeeByMerchant(Merchant merchant, FeeStatus status, DateInterval dateInterval);
	
}
