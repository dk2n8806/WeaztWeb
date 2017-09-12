package com.core.service.payment.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.payment.Payment;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.subscription.Subscription;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.payment.PaymentDao;
import com.core.dao.payment.SubscriptionPaymentDao;
import com.core.service.payment.SubscriptionPaymentService;

@Service
@Transactional
public class SubscriptionPaymentServiceImpl implements SubscriptionPaymentService{

	@Autowired private SubscriptionPaymentDao subscriptionPaymentDao;
	@Autowired private PaymentDao paymentDao;
	
	@Override
	public SubscriptionPayment findById(Long id) {
		return subscriptionPaymentDao.findById(id);
	}

	@Override
	public SubscriptionPayment getReference(Long id) {
		return subscriptionPaymentDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return subscriptionPaymentDao.getRowCount();
	}

	
	
	/** :::
	 * <p>Retrieve a list of payment by an account</p>
	 * {@link SubscriptionPaymentService#getByAccount(Account, PaymentStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<SubscriptionPayment> getByAccount(Account account
			, PaymentStatus status, DateInterval dateInterval, Pageable pageable) {
		if(account == null) 
			return new ArrayList<>();
		return subscriptionPaymentDao.getPayments(account, status, dateInterval, pageable);
	}

	
	

	/** :::
	 * <p>Retrieve total payments by the account</p>
	 * {@link SubscriptionPaymentService#countByAccount(Account, PaymentStatus, DateInterval)}
	 * ::: */
	@Override
	public long countByAccount(Account account, PaymentStatus status, DateInterval dateInterval) {
		if(account == null) return 0;
		try {
			return subscriptionPaymentDao.countPayments(account, status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	/** :::
	 * {@link SubscriptionPaymentService#getBySubscription(Subscription)}
	 */
	@Override
	public SubscriptionPayment getBySubscription(Subscription subscription) {
		try {
			return subscriptionPaymentDao.getBySubscription(subscription);
		} catch(NoResultException e) {
			return null;
		}
	}

	
	/** 
	 * {@link SubscriptionPaymentService#markAsVoided}
	 * */
	@Override
	public void markAsVoided(Payment payment) throws PaymentException {
		if(!payment.getStatus().equals(PaymentStatus.PENDING))
			throw new PaymentException("Invalid payment status");
		paymentDao.updatePaymentStatus(payment, PaymentStatus.PENDING, PaymentStatus.VOIDED);
	}

	
	/** 
	 * {@link SubscriptionPaymentService#markAsCompleted}
	 * */
	@Override
	public void markAsCompleted(Payment payment) throws PaymentException {
		if(!payment.getStatus().equals(PaymentStatus.PENDING))
			throw new PaymentException("Invalid payment status");
		paymentDao.updatePaymentStatus(payment, PaymentStatus.PENDING, PaymentStatus.COMPLETED);
		
	}

	
	/** :::
	 * <p>Trigger to request payment</p>
	 * 
	 * {@link SubscriptionPaymentService#triggerPayment(List)}
	 */
	@Override
	public void triggerPayment(List<Subscription> subscriptions) {
		if(subscriptions.size() > 0)
			subscriptionPaymentDao.updatePaymentRequestBySubscriptions(
										subscriptions, PaymentStatus.PENDING, false, true);
		
	}

}
