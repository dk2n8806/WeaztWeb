package com.core.service.subscription;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Unsubscription;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.subscription.impl.UnsubscriptionServiceImpl;

public interface UnsubscriptionService extends GenericService<Unsubscription, Long>{

	/** {@link UnsubscriptionServiceImpl#save(Subscription)} */
	Unsubscription save(Subscription subscription);

	/** {@link UnsubscriptionServiceImpl#getUnsubscriptions(DateInterval, Pageable)} */
	List<Unsubscription> getUnsubscriptions(DateInterval dateInterval, Pageable pageable);
	
	/** {@link UnsubscriptionServiceImpl#getByAccount(Account, DateInterval, Pageable)} */
	List<Unsubscription> getByAccount(Account account, DateInterval dateInterval, Pageable pageable);
	
	/** {@link UnsubscriptionServiceImpl#countUnsubscriptions(DateInterval)} */
	long countUnsubscriptions(DateInterval dateInterval);
	
	/** {@link UnsubscriptionServiceImpl#countByAccount(Account, DateInterval)} */
	long countByAccount(Account account, DateInterval dateInterval);
}
