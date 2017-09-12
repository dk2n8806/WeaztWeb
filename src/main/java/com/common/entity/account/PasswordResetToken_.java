package com.common.entity.account;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceToken_;

@StaticMetamodel(PasswordResetToken.class)
public class PasswordResetToken_ extends AbstractPersistenceToken_{

	public static volatile SingularAttribute<PasswordResetToken, Date> expiryDate;
	public static volatile SingularAttribute<PasswordResetToken, Account> account;
}
