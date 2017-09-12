package com.common.adapter.shippo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;

@StaticMetamodel(ShippoParcelAdapter.class)
public class ShippoParcelAdapter_ {


	public static volatile SingularAttribute<ShippoParcelAdapter, String> token;
	public static volatile SingularAttribute<ShippoParcelAdapter, Weight> weight;
	public static volatile SingularAttribute<ShippoParcelAdapter, Measurement> measurement;
}
