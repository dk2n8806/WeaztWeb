package com.core.dao.payment;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.payment.Payment;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.payment.impl.PaymentDaoImpl;

public interface PaymentDao extends GenericRepository<Payment, Long>
{

	/** {@link PaymentDaoImpl#getPayments} */
	List<Payment> getPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval, Pageable pageable);
	
	/** {@link PaymentDaoImpl#countPayments} */
	long countPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval);
	
	/** {@link PaymentDaoImpl#updatePaymentStatus} */
	void updatePaymentStatus(Payment payment, PaymentStatus oldStatus, PaymentStatus newStatus);
}
