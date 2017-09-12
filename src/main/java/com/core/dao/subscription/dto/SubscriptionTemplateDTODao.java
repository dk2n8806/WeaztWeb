package com.core.dao.subscription.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface SubscriptionTemplateDTODao extends GenericRepository<SubscriptionTemplateDTO, Long>{

	/** {@link SubscriptionTemplateDTODaoImpl#getTemplate(Subscription)} */
	SubscriptionTemplateDTO getTemplate(Subscription subscription);
	
	
	/** {@link SubscriptionTemplateDTODaoImpl#getTemplates(Account, SubscriptionStatus, DateInterval, Pageable)} */
	List<SubscriptionTemplateDTO> getTemplates(Account account
			, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable);
	
}
