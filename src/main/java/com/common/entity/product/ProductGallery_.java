package com.common.entity.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.entity.AbstractPersistenceObject_;
import com.common.entity.support.embeded.ImagePath;

@StaticMetamodel(ProductGallery.class)
public class ProductGallery_ extends AbstractPersistenceObject_
{

	public static volatile SingularAttribute<ProductGallery, ImagePath> imagePath;
	public static volatile SingularAttribute<ProductGallery, Product> product;
	
	
}
