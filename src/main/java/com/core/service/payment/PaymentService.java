package com.core.service.payment;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.payment.Payment;
import com.common.entity.payment.PaymentTransaction;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.payment.impl.PaymentServiceImpl;

public interface PaymentService extends GenericService<Payment, Long> {
	
	/** {@link PaymentServiceImpl#save(Payment)} */
	Payment save(Payment payment);
	
	void update(List<Payment> payments);
	
	
	/** {@link PaymentServiceImpl#completed(Payment, PaymentTransaction)} */
	void completed(Payment payment, PaymentTransaction transaction)
																					throws PaymentException ;
	
	/** {@link PaymentServiceImpl#getPayments} */
	List<Payment> getPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval, Pageable pageable);
	
	/** {@link PaymentServiceImpl#countPayments} */
	long countPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval);
}
