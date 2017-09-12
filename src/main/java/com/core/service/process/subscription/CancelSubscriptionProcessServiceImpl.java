package com.core.service.process.subscription;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.payment.Payment;
import com.common.entity.refund.SubscriptionRefund;
import com.common.entity.refund.Refund;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Unsubscription;
import com.common.exception.PaymentException;
import com.common.exception.SubscriptionException;
import com.common.type.SubscriptionStatus;
import com.core.service.order.OrderIntentService;
import com.core.service.payment.SubscriptionPaymentService;
import com.core.service.refund.RefundService;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.UnsubscriptionService;

@Service
@Transactional
public class CancelSubscriptionProcessServiceImpl implements ICancelSubscriptionProcessService {

	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private RefundService refundService;
	@Autowired private SubscriptionPaymentService paymentService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private UnsubscriptionService unsubscriptionService;
	


	/** :::
	 * <p>Process to cancel a subscription</p>
	 * 
	 * <ul>
	 * 		<li>Calculate refund for the subscription</li>
	 * 		<li>Cancel order_intents</li>
	 * </ul>
	 */
	@Override
	public void cancel(Subscription subscription) throws SubscriptionException {
		
		// Handle subscription payment and refund accordingly.
		switch (subscription.getStatus()) {
		case SUBSCRIBED: refundPaymentHandler(subscription); break;
		case SUBSCRIBING: voidPaymentHandler(subscription); break;
		default: throw new SubscriptionException("Error while canceling the subscription");
		}
	
		subscriptionService.markAsUnsubscribed(subscription.getId());		// Unsubscribe the subscripiton	
		orderIntentService.cancelBySubscription(subscription);					// Cancel order intent by the subscription

		
		// Create a new unsubscription entity for the record.
		subscription.setStatus(SubscriptionStatus.UNSUBSCRIBED);
		Unsubscription unsubscription = unsubscriptionService.save(subscription);		
		if(unsubscription == null)
			throw new SubscriptionException("Unable to create unsubscription for the subscription");
	}

	
	
	/**
	 * Cancel the subscriptions will void the payment. 
	 **/
	public void voidPaymentHandler(Subscription subscription) {
		Payment payment = paymentService.getBySubscription(subscription);
		try {
			paymentService.markAsVoided(payment);
		} catch (PaymentException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	/**
	 * Calculate and create a refund for the subscription
	 */
	public void refundPaymentHandler(Subscription subscription) throws SubscriptionException 
	{
		Refund refund = refundService.save(SubscriptionRefund.create(
																		subscription.getAccount(), subscription));
		if(refund == null) 
			throw new SubscriptionException("Unable to create a refund");
	}
	
}