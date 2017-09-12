package com.common.entity.merchant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.support.embeded.Phone;

@StaticMetamodel(MerchantProfile.class)
public class MerchantProfile_ extends AbstractPersistenceObject_ {

	public static SingularAttribute<MerchantProfile, Phone> phone;
	//public static SingularAttribute<MerchantProfile, Address> address;
}
