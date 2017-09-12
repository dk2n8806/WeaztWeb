package com.common.entity.subscription;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.type.SubscriptionStatus;

@StaticMetamodel(Subscription.class)
public class Subscription_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<Subscription, Product> 					product;
	public static volatile SingularAttribute<Subscription, Account> 				account;
	public static volatile SingularAttribute<Subscription, Integer> 					totalCharge;
	public static volatile SingularAttribute<Subscription, Date> 						scheduledOn;
	public static volatile SingularAttribute<Subscription, SubscriptionStatus>	status;
	public static volatile SingularAttribute<Subscription, Integer> 					hadShipped;
	public static volatile SingularAttribute<Subscription, Integer> 					frequency;
	public static volatile SingularAttribute<Subscription, Integer> 					nos;
	public static volatile SingularAttribute<Subscription, ValuePerShipment> 	perShipment;
	
	
}
