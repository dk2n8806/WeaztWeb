package com.core.dao.subscription.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.subscription.UnsubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface UnsubscriptionTemplateDTODao 
extends GenericRepository<UnsubscriptionTemplateDTO, Long>{

	
	List<UnsubscriptionTemplateDTO> getTemplates(Account account, DateInterval dateInterval, Pageable pageable);
}
