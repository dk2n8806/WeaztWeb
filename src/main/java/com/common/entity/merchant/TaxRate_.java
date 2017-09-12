package com.common.entity.merchant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;

@StaticMetamodel(TaxRate.class)
public class TaxRate_  extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<TaxRate, Integer> rateValue;
}
