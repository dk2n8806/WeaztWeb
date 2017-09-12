package com.core.service.support;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.support.ContactSupportMessage;
import com.common.entity.support.SupportCategory;
import com.common.util.date.DateInterval;
import com.core.service.support.imp.ContactSupportMessageServiceImpl;

public interface ContactSupportMessageService {

	/** {@link ContactSupportMessageServiceImpl#save(Account, String, SupportCategory, String)} */
	ContactSupportMessage save(Account account
			, String subject, SupportCategory category, String message);
	
	
	/** {@link ContactSupportMessageServiceImpl#countMessage(SupportCategory, Boolean, DateInterval)} */
	long countMessage(SupportCategory category, Boolean isRead, DateInterval dateInterval);
	
	/** {@link ContactSupportMessageServiceImpl#countByAccount(Account, SupportCategory, Boolean, DateInterval)} */
	long countByAccount(Account account, SupportCategory category, Boolean isRead, DateInterval dateInterval);
	
	
	/** {@link ContactSupportMessageServiceImpl#getMessages(SupportCategory, Boolean, DateInterval, Pageable)} */
	List<ContactSupportMessage> getMessages(SupportCategory category, Boolean isRead, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link ContactSupportMessageServiceImpl#getByAccount(Account, SupportCategory, Boolean, DateInterval, Pageable)} */
	List<ContactSupportMessage> getByAccount(Account account, SupportCategory category, Boolean isRead, DateInterval dateInterval, Pageable pageable);
	
}
