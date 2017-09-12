package com.core.service.product;

import java.util.List;

import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.core.service.GenericService;
import com.core.service.product.impl.ParcelServiceImpl;

public interface ParcelService extends GenericService<Parcel, Long>{

	/** {@link ParcelServiceImpl#save(Product, ParcelAdapter)} */
	Parcel save(Product product, ParcelAdapter parcelAdapter);
	
	
	/** {@link ParcelServiceImpl#getParcel(Product)} */
	Parcel getParcel(Product product);
	
	
	/** {@link ParcelServiceImpl#getByProduct(Product, Boolean)} */
	List<Parcel> getByProduct(Product product, Boolean isActive);
	
	
	/** {@link ParcelServiceImpl#countByProduct(Product, Boolean)} */
	long countByProduct(Product product, Boolean isActive);
	
}
