package com.core.dao.payout;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.MerchantFee;
import com.common.type.FeeStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.payout.impl.MerchantFeeDaoImpl;

public interface MerchantFeeDao extends GenericRepository<MerchantFee, Long>{

	/** {@link MerchantFeeDaoImpl#getFee(Merchant, FeeStatus, DateInterval, Pageable)} */
	List<MerchantFee> getFee(Merchant merchant, FeeStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link MerchantFeeDaoImpl#countFees(Merchant, FeeStatus, DateInterval)} */
	long countFees(Merchant merchant, FeeStatus status, DateInterval dateInterval);
	
	/** {@link MerchantFeeDaoImpl#getTotalFee(Merchant, FeeStatus, DateInterval)} */
	long getTotalFee(Merchant merchant, FeeStatus status, DateInterval dateInterval);
}
