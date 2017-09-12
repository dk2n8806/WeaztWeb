package com.common.entity.support;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.account.Account;

@StaticMetamodel(ContactSupportMessage.class)
public class ContactSupportMessage_ extends AbstractPersistenceObject_{

	
	public static volatile SingularAttribute<ContactSupportMessage, Account> account;
	public static volatile SingularAttribute<ContactSupportMessage, String> subject;
	public static volatile SingularAttribute<ContactSupportMessage, String> message;
	public static volatile SingularAttribute<ContactSupportMessage, SupportCategory> category;
	public static volatile SingularAttribute<ContactSupportMessage, Boolean> read;
	
	
}
