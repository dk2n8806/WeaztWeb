package com.core.dao.refund;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.refund.Refund;
import com.common.entity.refund.RefundStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface RefundDao extends GenericRepository<Refund, Long>{

	List<Refund> getList(Account account, RefundStatus status, DateInterval dateInterval, Pageable pageable);
	
	long count(Account account, RefundStatus status, DateInterval dateInterval);
}
