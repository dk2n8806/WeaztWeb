package com.core.service.promo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.promo.Promotion;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.promo.impl.PromotionServiceImpl;

public interface PromotionService extends GenericService<Promotion, Long>{


	/** {@link PromotionServiceImpl#save(String, int, Date, Date)} */
	Promotion save(String code, int value, Date startOn, Date endOn);
	
	/** {@link PromotionServiceImpl#getByCode(String)}*/
	Promotion getByCode(String code);
	
	/** {@link PromotionServiceImpl#getPromotions(Boolean, DateInterval, Pageable)} */
	List<Promotion> getPromotions(Boolean isActive, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link PromotionServiceImpl#countPromotions(Boolean, DateInterval)} */
	long countPromotions(Boolean isActive, DateInterval dateInterval);
}
