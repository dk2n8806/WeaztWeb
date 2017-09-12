package com.common.entity.notification;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.refund.Refund;

@StaticMetamodel(RefundNotification.class)
public class RefundNotification_ extends Notification_{

	public static volatile SingularAttribute<RefundNotification, Refund> refund;
}
