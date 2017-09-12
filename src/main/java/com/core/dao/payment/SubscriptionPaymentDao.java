package com.core.dao.payment;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.subscription.Subscription;
import com.common.type.PaymentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;
import com.core.dao.payment.impl.SubscriptionPaymentDaoImpl;

public interface SubscriptionPaymentDao
	extends GenericRepository<SubscriptionPayment, Long>
{

	/** {@link SubscriptionPaymentDaoImpl#updatePaymentRequestBySubscriptions(List, PaymentStatus, Boolean, Boolean)} */
	void updatePaymentRequestBySubscriptions(
			List<Subscription> subscriptions, PaymentStatus status, Boolean curRequest, Boolean isRequest);
	
	
	/** {@link SubscriptionPaymentDaoImpl#getBySubscription(Subscription)} */
	SubscriptionPayment getBySubscription(Subscription subscription);
	
	/** :::
	 * Lookup a list of subscription_payment entities
	 * {@link SubscriptionPaymentDaoImpl#getPayments(Account, PaymentStatus, DateInterval, Pageable)}
	 * ::: */
	List<SubscriptionPayment> getPayments(Account account, PaymentStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** :::
	 * Count total subscription_payment entities
	 * {@link SubscriptionPaymentDaoImpl#countPayments(Account, PaymentStatus, DateInterval)}
	 * ::: */
	long countPayments(Account account, PaymentStatus status, DateInterval dateInterval);
}
