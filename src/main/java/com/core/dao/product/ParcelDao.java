package com.core.dao.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.product.Parcel;
import com.common.entity.product.Product;
import com.core.dao.generic.GenericRepository;
import com.core.dao.product.impl.ParcelDaoImpl;

public interface ParcelDao extends GenericRepository<Parcel, Long>{

	/** {@link ParcelDaoImpl#getParcels(Product, Boolean, Pageable)} */
	List<Parcel> getParcels(Product product, Boolean isActive, Pageable pageable);
	
	/** {@link ParcelDaoImpl#countParcels(Product, Boolean)} */
	long countParcels(Product product, Boolean isActive);
	
}
