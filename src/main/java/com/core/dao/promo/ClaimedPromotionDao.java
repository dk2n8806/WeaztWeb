package com.core.dao.promo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.Promotion;
import com.core.dao.generic.GenericRepository;
import com.core.dao.promo.impl.ClaimedPromotionDaoImpl;

public interface ClaimedPromotionDao 
extends GenericRepository<ClaimedPromotion, Long>{

	/** {@link ClaimedPromotionDaoImpl#getByAccount(Account, Promotion)} */
	ClaimedPromotion getByAccount(Account account, Promotion promotion);
	
	/** {@link ClaimedPromotionDaoImpl#getByAccount(Account, Pageable)} */
	List<ClaimedPromotion> getByAccount(Account account, Pageable pageable);
	
	/** {@link ClaimedPromotionDaoImpl#countByAccount(Account)} */
	long countByAccount(Account account);
	
	
}
