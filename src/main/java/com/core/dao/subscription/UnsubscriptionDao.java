package com.core.dao.subscription;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.subscription.Unsubscription;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.subscription.impl.UnsubscriptionDaoImpl;

public interface UnsubscriptionDao extends GenericRepository<Unsubscription, Long>{

	/** {@link UnsubscriptionDaoImpl#getUnsubscriptions(Account, DateInterval, Pageable)} */
	List<Unsubscription> getUnsubscriptions(Account account, DateInterval dateInterval, Pageable pageable);
	
	long countUnsubscriptions(Account account, DateInterval dateInterval);
}
