package com.common.entity.support.embeded;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Phone.class)
public class Phone_ {

	public static volatile SingularAttribute<Phone, String> number;
	public static volatile SingularAttribute<Phone, String> displayableNumber;
	public static volatile SingularAttribute<Phone, Boolean> active;
	public static volatile SingularAttribute<Phone, Boolean> verified;
	
	
}
