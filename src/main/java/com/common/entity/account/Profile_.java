package com.common.entity.account;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.address.Address;
import com.common.entity.support.embeded.BirthDate;
import com.common.entity.support.embeded.Phone;
import com.common.type.Gender;

@StaticMetamodel(Profile.class)
public class Profile_ extends AbstractPersistenceObject_ {


	public static volatile SingularAttribute<Profile, Gender> gender;
	public static volatile SingularAttribute<Profile, BirthDate> birthDate;
	public static volatile SingularAttribute<Profile, Address> address;
	public static volatile SingularAttribute<Profile, Phone> phone;
	public static volatile SingularAttribute<Profile, String> fullName;
}
