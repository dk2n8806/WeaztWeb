package com.core.service.subscription.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.subscription.dto.SubscriptionTemplateDTODao;

@Service
@Transactional
public class SubscriptionTemplateDTOServiceImpl implements SubscriptionTemplateDTOService{

	@Autowired private SubscriptionTemplateDTODao templateDao;
	
	
	/** :::
	 * <p>Lookup a list of templates subscriptions by an account</p>
	 * 
	 * {@link SubscriptionTemplateDTOService#getTemplatesByAccount}
	 * ::: */
	@Override
	public List<SubscriptionTemplateDTO> getByAccount(
			Account account, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return templateDao.getTemplates(account, status, dateInterval, pageable);
	}

	
	

	/** :::
	 * <p>Lookp a list of templates subscriptions</p>
	 * {@link SubscriptionTemplateDTOService#getTemplates}
	 * :::  */
	@Override
	public List<SubscriptionTemplateDTO> getTemplates(
			SubscriptionStatus status, DateInterval dateInterval, Pageable pageable) {
		return templateDao.getTemplates(null, status, dateInterval, pageable);
	}
	
	
	

	/** :::
	 * <p>Lookup a template of a subscription</p>
	 * 
	 * {@link SubscriptionTemplateDTOService#getTemplate(Subscription)}
	 */
	@Override
	public SubscriptionTemplateDTO getTemplate(Subscription subscription) {
		if(subscription == null) return null;
		try {
			return templateDao.getTemplate(subscription);
		} catch(NoResultException e){
			return null;
		}
	}

}
