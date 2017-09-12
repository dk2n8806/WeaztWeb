package com.core.service.promo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.Promotion;
import com.core.service.GenericService;
import com.core.service.promo.impl.ClaimedPromotionServiceImpl;

public interface ClaimedPromotionService extends GenericService<ClaimedPromotion, Long>{

	/** {@link ClaimedPromotionServiceImpl#save(Account, Promotion)} */
	ClaimedPromotion save(Account account, Promotion promotion);
	

	/** {@link ClaimedPromotionServiceImpl#getByAccount(Account, Promotion)} */
	ClaimedPromotion getByAccount(Account account, Promotion promotion);
	

	/** {@link ClaimedPromotionServiceImpl#getByAccount(Account, Pageable)} */
	List<ClaimedPromotion> getByAccount(Account account, Pageable pageable);
	

	/** {@link ClaimedPromotionServiceImpl#countByAccount(Account)} */
	long countByAccount(Account account);
	
}
