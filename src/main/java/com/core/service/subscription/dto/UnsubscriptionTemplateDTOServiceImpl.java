package com.core.service.subscription.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.subscription.UnsubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.util.date.DateInterval;
import com.core.dao.subscription.dto.UnsubscriptionTemplateDTODao;

@Service
@Transactional
public class UnsubscriptionTemplateDTOServiceImpl implements UnsubscriptionTemplateDTOService{

	@Autowired private UnsubscriptionTemplateDTODao templateDao;
	
	@Override
	public List<UnsubscriptionTemplateDTO> getByAccount(Account account, DateInterval dateInterval, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return templateDao.getTemplates(account, dateInterval, pageable);
	}

}
