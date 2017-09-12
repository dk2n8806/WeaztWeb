package com.core.service.payment;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.payment.Payment;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.subscription.Subscription;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.service.GenericService;
import com.core.service.payment.impl.SubscriptionPaymentServiceImpl;


/** 
 * 
 * @author dk2n_
 *
 */
public interface SubscriptionPaymentService extends GenericService<SubscriptionPayment, Long>{

	/** {@link SubscriptionPaymentServiceImpl#getBySubscription(Subscription)} */
	SubscriptionPayment getBySubscription(Subscription subscription);
	
	
	/** {@link SubscriptionPaymentServiceImpl#getByAccount(Account, PaymentStatus, DateInterval, Pageable)} */
	List<SubscriptionPayment> getByAccount(Account account
								, PaymentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/**
	 * <p>Trigger to process payments on the subscriptions.</p> 
	 * {@link SubscriptionPaymentServiceImpl#triggerPayment(List)} */
	void triggerPayment(List<Subscription> subscriptions);
	
	
	
	/** {@link SubscriptionPaymentServiceImpl#countByAccount(Account, PaymentStatus, DateInterval)} */
	long countByAccount(Account account, PaymentStatus status, DateInterval dateInterval);
	
	
	/** {@link SubscriptionPaymentServiceImpl#markAsVoided(Payment)} */
	void markAsVoided(Payment payment) throws PaymentException;
	
	/** {@link SubscriptionPaymentServiceImpl#markAsCompleted(Payment)} */
	void markAsCompleted(Payment payment) throws PaymentException;
	
}
