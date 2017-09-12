package com;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.common.entity.payment.Payment;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.service.payment.PaymentService;
import com.core.service.process.payment.IChargePaymentService;

public class TestChargePayment extends BaseTest{

	
	@Autowired private IChargePaymentService chargeService;
	@Autowired private PaymentService paymentService;
	
	
	@Test
	public void test() {
		PaymentStatus status = PaymentStatus.PENDING;
		Boolean isRequested = true;
		DateInterval dateInterval = null;
		Pageable pageable = null;
		List<Payment> payments = paymentService.getPayments(status, isRequested, dateInterval, pageable );
	
		System.out.println(payments.size());
		
		for(Payment payment : payments) {
			try {
				chargeService.charge(payment);
			} catch (PaymentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
