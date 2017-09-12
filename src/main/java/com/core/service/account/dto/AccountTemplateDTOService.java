package com.core.service.account.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.AccountTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;

public interface AccountTemplateDTOService {

	/** {@link AccountTemplateDTOServiceImpl#getTemplates(Role, AccountStatus, DateInterval, Pageable)} */
	List<AccountTemplateDTO> getTemplates(Role role, AccountStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link AccountTemplateDTOServiceImpl#getTemplate(Account)} */
	AccountTemplateDTO getTemplate(Account account);
}
