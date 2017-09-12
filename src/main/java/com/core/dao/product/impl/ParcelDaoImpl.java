package com.core.dao.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.product.Parcel;
import com.common.entity.product.Parcel_;
import com.common.entity.product.Product;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.product.ParcelDao;

@Repository
public class ParcelDaoImpl extends GenericJpaRepository<Parcel, Long> implements ParcelDao{

	@Override
	public List<Parcel> getParcels(Product product, Boolean isActive, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Parcel> query = builder.createQuery(Parcel.class);
		Root<Parcel> root = query.from(Parcel.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(product != null)
			predicates.add(builder.equal(root.get(Parcel_.product), product));
		if(isActive != null) 
			predicates.add(builder.equal(root.get(Parcel_.active), isActive));
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	
	
	@Override
	public long countParcels(Product product, Boolean isActive) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Parcel> root = query.from(Parcel.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(product != null)
			predicates.add(builder.equal(root.get(Parcel_.product), product));
		if(isActive != null) 
			predicates.add(builder.equal(root.get(Parcel_.active), isActive));
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}

}
