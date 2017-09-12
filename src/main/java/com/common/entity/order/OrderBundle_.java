package com.common.entity.order;


import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.product.Product;
import com.common.type.OrderStatus;

@StaticMetamodel(OrderBundle.class)
public class OrderBundle_ extends AbstractPersistenceObject_{

	public static volatile SingularAttribute<OrderBundle, Product> product;
	public static volatile SingularAttribute<OrderBundle, Integer> collectedAmount;
	public static volatile SingularAttribute<OrderBundle, Integer> serviceFee;
	public static volatile SingularAttribute<OrderBundle, OrderStatus> status;
	public static volatile SingularAttribute<OrderBundle, Integer> noo;												
	public static volatile 		  ListAttribute<OrderBundle, OrderTransaction> orderTransactions;
	
	
}
