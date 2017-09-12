package com.common.entity.notification;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(Notification.class)
public class Notification_ extends AbstractPersistenceObject_{

		public static volatile SingularAttribute<Notification, Account> account;
}
