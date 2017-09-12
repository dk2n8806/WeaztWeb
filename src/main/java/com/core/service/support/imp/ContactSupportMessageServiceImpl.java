package com.core.service.support.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.support.ContactSupportMessage;
import com.common.entity.support.SupportCategory;
import com.common.util.date.DateInterval;
import com.core.dao.support.ContactSupportMessageDao;
import com.core.service.support.ContactSupportMessageService;

@Service
@Transactional
public class ContactSupportMessageServiceImpl implements ContactSupportMessageService {

	@Autowired private ContactSupportMessageDao supportDao;
	
	
/**
 * Create and persist a new message
 * {@link ContactSupportMessageService#save(Account, String, SupportCategory, String)}
 */
	@Override
	public ContactSupportMessage save(Account account, String subject,
			SupportCategory category, String message) {
		ContactSupportMessage support = ContactSupportMessage.create(account, subject, category, message);
		if(support != null) {
			supportDao.persist(support);
		}
		return support;
	}
	
	
/**
 * Count total message
 * {@link ContactSupportMessageService#countMessage(SupportCategory, Boolean, DateInterval)}
 */
	@Override
	public long countMessage(SupportCategory category, Boolean isRead,
			DateInterval dateInterval) {
		try {
			return supportDao.countMessage(null, category, isRead, dateInterval);
		} catch(NoResultException e){
			return 0;
		}
	}
	
	
/**
 * Count total messages by account
 * {@link ContactSupportMessageService#countByAccount(Account, SupportCategory, Boolean, DateInterval)}
 */
	@Override
	public long countByAccount(Account account, SupportCategory category,
			Boolean isRead, DateInterval dateInterval) {
		if(account == null) {
			return 0;
		}
		try {
			return supportDao.countMessage(account, category, isRead, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	
/**
 * Retrieve a list of message
 * {@link ContactSupportMessageService#getMessages(SupportCategory, Boolean, DateInterval, Pageable)}
 */
	@Override
	public List<ContactSupportMessage> getMessages(SupportCategory category,
			Boolean isRead, DateInterval dateInterval, Pageable pageable) {
		return supportDao.getMessages(null, category, isRead, dateInterval, pageable);
	}
	
	
	
	
	
/**
 * Retrieve a list of message by account
 * {@link ContactSupportMessageService#getByAccount(Account, SupportCategory, Boolean, DateInterval, Pageable)}
 */
	@Override
	public List<ContactSupportMessage> getByAccount(Account account,
			SupportCategory category, Boolean isRead, DateInterval dateInterval,
			Pageable pageable) {
		if(account == null) {
			return new ArrayList<>();
		}
		return supportDao.getMessages(account, category, isRead, dateInterval, pageable);
	}

}
