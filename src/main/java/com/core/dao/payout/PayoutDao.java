package com.core.dao.payout;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.Payout;
import com.common.type.PayoutStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.payout.impl.PayoutDaoImpl;

public interface PayoutDao extends GenericRepository<Payout, Long>{

	/** {@link PayoutDaoImpl#getTotalAmount} */
	long getTotalAmount(Merchant merchant, PayoutStatus status, DateInterval dateInterval);
	
	/** {@link PayoutDaoImpl#getPayouts} */
	List<Payout> getPayouts(Merchant merchant, PayoutStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link PayoutDaoImpl#countPayouts} */
	long countPayouts(Merchant merchant, PayoutStatus status, DateInterval dateInterval);
}
