package com.core.service.payout;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.Payout;
import com.common.type.PayoutStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.payout.impl.PayoutServiceImpl;

public interface PayoutService extends GenericService<Payout, Long>{

	/** {@link PayoutServiceImpl#save(Payout)} */
	Payout save(Payout payout);
	
	/** {@link PayoutServiceImpl#getPayouts(PayoutStatus, DateInterval, Pageable)} */
	List<Payout> getPayouts(PayoutStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link PayoutServiceImpl#countPayouts(PayoutStatus, DateInterval)} */
	long countPayouts(PayoutStatus status, DateInterval dateInterval);
	
	int getTotalAmount(PayoutStatus status, DateInterval dateInterval);
	

	/** {@link PayoutServiceImpl#countByMerchant} */
	long countByMerchant(Merchant merchant, PayoutStatus status, DateInterval dateInterval);

	/** {@link PayoutServiceImpl#getByMerchant} */
	List<Payout> getByMerchant(Merchant merchant, PayoutStatus status, DateInterval dateInterval, Pageable pageable);

	/** {@link PayoutServiceImpl#getTotalAmountByMerchant} */
	int getTotalAmountByMerchant(Merchant merchant, PayoutStatus status, DateInterval dateInterval);
}
