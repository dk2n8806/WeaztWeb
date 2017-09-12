package com.core.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.core.dao.product.ParcelDao;
import com.core.service.product.ParcelService;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService{

	@Autowired private ParcelDao parcelDao;

	@Override
	public Parcel findById(Long id) {
		return parcelDao.findById(id);
	}

	@Override
	public Parcel getReference(Long id) {
		return parcelDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return parcelDao.getRowCount();
	}

	@Override
	public List<Parcel> getByProduct(Product product, Boolean isActive) {
		if(product == null) return new ArrayList<>();
		return parcelDao.getParcels(product, isActive, null);
	}

	@Override
	public long countByProduct(Product product, Boolean isActive) {
		if(product == null) return 0;
		try {
			return parcelDao.countParcels(product, isActive);
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	
	
	/** :::
	 * <p>Lookup recent active parcel of the product</p>
	 * 
	 * {@link ParcelService#getParcel(Product)}
	 * ::: */
	@Override
	public Parcel getParcel(Product product) {
		List<Parcel> parcels = getByProduct(product, true);
		return parcels.size() == 0 ? null : parcels.get(0);
	}

		
	
	
	/** :::
	 * <p>Create & save a new parcel entity</p>
	 * 
	 * {@link ParcelService#save(Product, ParcelAdapter)}
	 */
	@Override
	public Parcel save(Product product, ParcelAdapter parcelAdapter) {
		Parcel parcel = Parcel.create(product, parcelAdapter);
		if(parcel != null) {
			Parcel curr = getParcel(product);
			if(curr != null){
				curr.setActive(false);
				parcelDao.update(curr);
			}
			parcelDao.persist(parcel);
		}
		return parcel;
	}
	
	
}
