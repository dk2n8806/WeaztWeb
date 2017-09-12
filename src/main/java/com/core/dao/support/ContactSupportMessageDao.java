package com.core.dao.support;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.support.ContactSupportMessage;
import com.common.entity.support.SupportCategory;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.support.impl.ContactSupportMessageDaoImpl;

public interface ContactSupportMessageDao 
extends GenericRepository<ContactSupportMessage, Long>{

	/** {@link ContactSupportMessageDaoImpl#countMessage(Account, SupportCategory, Boolean, DateInterval)} */
	long countMessage(Account account, SupportCategory category, Boolean isRead, DateInterval dateInterval);
	
	/** {@link ContactSupportMessageDaoImpl#getMessages(Account, SupportCategory, Boolean, DateInterval, Pageable)} */
	List<ContactSupportMessage> getMessages(Account account, SupportCategory category, Boolean isRead, DateInterval dateInterval, Pageable pageable);
	
}
