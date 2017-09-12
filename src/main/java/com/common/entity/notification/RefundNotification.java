package com.common.entity.notification;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.refund.Refund;

@Entity
@Table(name="NOTIFICATION_REFUND")
public class RefundNotification extends Notification {

	private static final long serialVersionUID = 1L;

	private Refund refund;
	
	public static RefundNotification create(Refund refund) {
		try {
			
			if(refund == null || refund.getAccount() == null)
				throw new IllegalArgumentException("Refund entity is required");
	
			
			RefundNotification notification = new RefundNotification();
			notification.setAccount(refund.getAccount());
			notification.setRefund(refund);
			return notification;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	@ManyToOne
	@JoinColumn(name="REFUND_ID", nullable=false, updatable=false)
	public Refund getRefund() {	return refund;}
	public void setRefund(Refund refund) {	this.refund = refund;}
	
}
