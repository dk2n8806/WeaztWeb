package com.core.service.process.payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.payment.Payment;
import com.common.entity.payment.PaymentTransaction;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.common.exception.PaymentException;
import com.common.type.PaymentStatus;
import com.core.service.payment.PaymentService;
import com.core.service.stripe.StripeCreatorService;
import com.core.service.stripe.StripeCustomerService;

@Service
@Transactional
public class ChargePaymentServiceImpl implements IChargePaymentService{

	@Autowired private StripeCreatorService stripeService;
	@Autowired private StripeCustomerService stripeCustomerService;
	@Autowired private PaymentService paymentService;
	
	/** :::
	 * <p>Charge a payment</p>
	 * 
	 * {@link IChargePaymentService#charge(Payment)}
	 */
	@Override
	public void charge(Payment payment) throws PaymentException {
		if(payment.getStatus() != PaymentStatus.PENDING) 
			throw new PaymentException("Invalid payment status");
		if(!payment.isRequested())
			throw new PaymentException("payment has not requested");
		
		
		if(payment instanceof SubscriptionPayment)
			paymentService.completed(payment
										, PaymentTransaction.create(
												handleSubscriptionPayment((SubscriptionPayment)payment)));
		else 
			throw new PaymentException("Transaction payment not support");
		

	}
	

	
	/** :::
	 * <p>Create charge for each payment</p>
	 * ::: */
	private StripeCharge handleSubscriptionPayment(SubscriptionPayment payment) throws PaymentException {
		System.out.println("Handle Subscription payment");
		StripeCustomer stripeCustomer  = stripeCustomerService.getDefaultByAccount(payment.getAccount());
		if(stripeCustomer == null)
			throw new PaymentException("Unable to retrieve payment_method of the account");
		System.out.println("stripe customer id: " + stripeCustomer.getId());
		int chargeAmount = payment.getAmount();
		StripeCharge charge = stripeService.createCharge(stripeCustomer, chargeAmount);
		
		if(charge != null)
			System.out.println("charge is not null");
		return charge;
	}

	
	

}
