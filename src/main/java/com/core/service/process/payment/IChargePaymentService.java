package com.core.service.process.payment;


import com.common.entity.payment.Payment;
import com.common.exception.PaymentException;

public interface IChargePaymentService {

	/** {@link ChargePaymentServiceImpl#charge(Payment)} */
	void charge(Payment payment) throws PaymentException;
	
}
