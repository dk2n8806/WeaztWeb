package com.core.dao.promo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.promo.Promotion;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.promo.impl.PromotionDaoImpl;

/** 
 * 
 * @author dk2n_
 *
 */
public interface PromotionDao extends GenericRepository<Promotion, Long>{

	/** {@link PromotionDaoImpl#getByCode(String)} */
	Promotion getByCode(String code);
	
	/** {@link PromotionDaoImpl#getPromotions(Boolean, DateInterval, Pageable)}*/
	List<Promotion> getPromotions(Boolean isActive, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link PromotionDaoImpl#countPromotions(Boolean, DateInterval)} */
	long countPromotions(Boolean isActive, DateInterval dateInterval);
	
}
