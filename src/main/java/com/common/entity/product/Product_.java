package com.common.entity.product;


import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.support.embeded.ImagePath;
import com.common.type.ProductStatus;

@StaticMetamodel(Product.class)
public class Product_ extends AbstractPersistenceObject_ {


	public static volatile SingularAttribute<Product, Category> 				category;
	public static volatile SingularAttribute<Product, Merchant> 				merchant;
	public static volatile SingularAttribute<Product, ProductStatus> 		status;
	public static volatile 	   ListAttribute<Product, ProductGallery> 		gallery;
	public static volatile SingularAttribute<Product, SubscriptionInfo> 	subscriptionInfo;
	public static volatile SingularAttribute<Product, BasicInfo> 				basicInfo;
	public static volatile SingularAttribute<Product, ShippingInfo> 			shippingInfo;
	public static volatile SingularAttribute<Product, ImagePath>   			displayImage;
	
	

	
}
