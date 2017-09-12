package com.common.adapter.shippo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.address.Address_;

@StaticMetamodel(ShippoAddressAdapter.class)
public class ShippoAddressAdapter_  extends Address_ {

	public static volatile SingularAttribute<ShippoAddressAdapter, String> token;
}
