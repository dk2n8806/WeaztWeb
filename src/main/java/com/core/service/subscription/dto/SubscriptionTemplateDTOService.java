package com.core.service.subscription.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;

public interface SubscriptionTemplateDTOService {

	/** {@link SubscriptionTemplateDTOServiceImpl#getTemplate} */
	SubscriptionTemplateDTO getTemplate(Subscription subscription);
	
	
	/** {@link SubscriptionTemplateDTOServiceImpl#getByAccount} */
	List<SubscriptionTemplateDTO> getByAccount(Account account
					, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable);

	
	/** {@link SubscriptionTemplateDTOServiceImpl#getTemplates} */
	List<SubscriptionTemplateDTO> getTemplates(SubscriptionStatus status
																, DateInterval dateInterval, Pageable pageable);
}
