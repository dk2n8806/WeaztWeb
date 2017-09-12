package com.core.service.refund;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.refund.Refund;
import com.common.entity.refund.RefundStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.refund.impl.RefundServiceImpl;

/** 
 * 
 * @author dk2n_
 *
 */
public interface RefundService extends GenericService<Refund, Long>{

	/** {@link RefundServiceImpl#save(Refund)} */
	Refund save(Refund refund);
	
	List<Refund> getList(RefundStatus status, DateInterval dateInterval, Pageable pageable);
	
	List<Refund> getByAccount(Account account, RefundStatus status, DateInterval dateInterval, Pageable pageable);
	
	long count(RefundStatus status, DateInterval dateInterval);
	
	long countByAccount(Account account, RefundStatus status, DateInterval dateInterval);
}
