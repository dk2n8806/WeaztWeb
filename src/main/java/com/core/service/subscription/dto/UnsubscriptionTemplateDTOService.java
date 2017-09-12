package com.core.service.subscription.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.subscription.UnsubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.date.DateInterval;

public interface UnsubscriptionTemplateDTOService {

	List<UnsubscriptionTemplateDTO> getByAccount(Account account, DateInterval dateInterval, Pageable pageable);
}
