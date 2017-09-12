package com.core.service.account.dto;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.AccountTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.account.dto.AccountTemplateDTODao;

@Service
@Transactional(readOnly=true)
public class AccountTemplateDTOServiceImpl  implements AccountTemplateDTOService
{

	@Autowired AccountTemplateDTODao templateDTODao;

	@Override
	public List<AccountTemplateDTO> getTemplates(Role role
						, AccountStatus status,  DateInterval dateInterval, Pageable pageable) 
	{
		return templateDTODao.getTemplates(role, status, dateInterval, pageable);
	}

	
	
	
	@Override
	public AccountTemplateDTO getTemplate(Account account) {
		try {
			return templateDTODao.getTemplate(account);
		} catch(NoResultException e) {
			return null;
		}
	}
}
