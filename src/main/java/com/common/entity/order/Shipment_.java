package com.common.entity.order;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.shippo.LabelAdapter;
import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.address.Address;
import com.common.type.ShipmentStatus;

@StaticMetamodel(Shipment.class)
public class Shipment_ extends AbstractPersistenceObject_{


	public static volatile SingularAttribute<Shipment, Address> receiver;
	public static volatile SingularAttribute<Shipment, Address> sender;
	public static volatile SingularAttribute<Shipment, ShipmentStatus> status;
	public static volatile SingularAttribute<Shipment, LabelAdapter> labelAdapter;
}
