package com.common.entity.account;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceToken_;


@StaticMetamodel(VerificationToken.class)
public class VerificationToken_ extends AbstractPersistenceToken_{

	public static volatile SingularAttribute<VerificationToken, Date> expiryDate;
	public static volatile SingularAttribute<VerificationToken, Account> account;
	
}
