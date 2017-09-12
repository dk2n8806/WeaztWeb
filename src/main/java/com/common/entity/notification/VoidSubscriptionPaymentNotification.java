package com.common.entity.notification;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.payment.Payment;
import com.common.entity.payment.SubscriptionPayment;
import com.common.type.PaymentStatus;

@Entity
@Table(name="NOTIFICATION_VOID_PAYMENT")
public class VoidSubscriptionPaymentNotification extends Notification{

	private static final long serialVersionUID = 1L;
	private Payment payment;
	
	
	
	public static VoidSubscriptionPaymentNotification create(Payment payment) {
		try {
			if(!payment.getStatus().equals(PaymentStatus.PENDING))
				throw new IllegalArgumentException("Invalid payment status");
			if(payment instanceof SubscriptionPayment) 
				throw new IllegalArgumentException("Subscription_Payment entity is required");
			
			SubscriptionPayment p = (SubscriptionPayment)payment;
			
			VoidSubscriptionPaymentNotification notification 
										= new VoidSubscriptionPaymentNotification();
			notification.payment = p;
			notification.setAccount( p.getAccount());
			return notification;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PAYMENT_ID", nullable=false, updatable=false)
	public Payment getPayment() {	return payment;}
	public void setPayment(Payment payment) {	this.payment = payment;}
}
