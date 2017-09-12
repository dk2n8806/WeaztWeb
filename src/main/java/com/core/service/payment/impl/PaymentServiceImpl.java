package com.core.service.payment.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.payment.Payment;
import com.common.entity.payment.PaymentTransaction;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.payment.PaymentDao;
import com.core.service.payment.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

	@Autowired private PaymentDao paymentDao;
	
	@Override
	public Payment save(Payment payment) {
		if(payment != null) 
			paymentDao.persist(payment);
		return payment;
	}

	@Override
	public Payment findById(Long id) {
		return paymentDao.findById(id);
	}

	@Override
	public Payment getReference(Long id) {
		return paymentDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return paymentDao.getRowCount();
	}

	@Override
	public void update(List<Payment> payments) {
		if(payments.size() > 0) {
			paymentDao.update(payments);
		}
	}

	
	/** :::
	 * {@link PaymentService#getPayments(}
	 */
	@Override
	public List<Payment> getPayments(PaymentStatus status
				, Boolean isRequested, DateInterval dateInterval, Pageable pageable) 
	{
		return paymentDao.getPayments(status, isRequested, dateInterval, pageable);
	}

	
	/** :::
	 * {@link PaymentService#countPayments}
	 */
	@Override
	public long countPayments(PaymentStatus status, Boolean isRequested, DateInterval dateInterval) {
		try {
			return paymentDao.countPayments(status, isRequested, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	/** :::
	 * <p>Inject a transaction to a payment</p>
	 * 
	 * {@link PaymentService#completed(Payment, PaymentTransaction)}
	 * ::: */
	@Override
	public void completed(Payment payment, PaymentTransaction transaction) throws PaymentException {
		if(payment.getStatus() != PaymentStatus.PENDING)
			throw new PaymentException("Invalid payment status");
		if(payment.getTransaction() != null)
			throw new PaymentException("Payment shouldn't have transaction yet");
		payment.setTransaction(transaction);
		payment.setStatus(PaymentStatus.COMPLETED);
		paymentDao.update(payment);
		
	}
}
 