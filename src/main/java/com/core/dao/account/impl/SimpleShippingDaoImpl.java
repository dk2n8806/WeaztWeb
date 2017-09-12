package com.core.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.account.SimpleShipping_;
import com.common.entity.address.Address;
import com.core.dao.account.SimpleShippingDao;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class SimpleShippingDaoImpl 
extends GenericJpaRepository<SimpleShipping, Long>
implements SimpleShippingDao {

		
	/** :::
	 * <p>Retrieve a list of shipping_info of an account</p>
	 * 
	 * @query
	 * [
	 * 		Select SS From {@link SimpleShipping} SS
	 * 		Where SS.account = :account
	 * 			And SS.primary = :isPrimary
	 * 			And SS.active = :isActive
	 * 		Order By SS DESC
	 * ]
	 * 
	 * {@link SimpleShippingDao#getByAccount(Account, Boolean, Pageable)}
	 * ::: */
	@Override
	public List<SimpleShipping> getByAccount(Account account, Boolean isPrimary, Boolean isActive, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SimpleShipping> query = builder.createQuery(SimpleShipping.class);
		Root<SimpleShipping> root = query.from(SimpleShipping.class);
		
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(SimpleShipping_.account), account));
		if(isPrimary != null) {
			predicates.add(builder.equal(root.get(SimpleShipping_.primary), isPrimary));
		}
		if(isActive != null) {
			predicates.add(builder.equal(root.get(SimpleShipping_.active), isActive));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root.get(SimpleShipping_.primary))
					, builder.desc(root));
		
		return getResultList(query, pageable);
	}

	
	
		
	/** :::
	 * <p>Count total shipping_info by an account</p>
	 * 
	 * @query
	 * [
	 * 		Select count(SS) From {@link SimpleShipping} SS
	 * 		Where SS.account = :account
	 * 			And SS.active = :isActive
	 * ]
	 * 
	 * {@link SimpleShippingDao#countByAccount(Account, Boolean)}
	 * ::: */
	@Override
	public long countByAccount(Account account, Boolean isActive) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SimpleShipping> root = query.from(SimpleShipping.class);
		
		Predicate activePredicate = null;
		if(isActive != null) {
			activePredicate = builder.equal(root.get(SimpleShipping_.active), isActive);
		}
		
		query.select(builder.count(root))
				.where(builder.equal(root.get(SimpleShipping_.account), account)
						, activePredicate);
		
		return entityManager.createQuery(query).getSingleResult();
	}




	
	/** 
	 * 
	 * {@link SimpleShippingDao#getPrimaryAddress(Account)}
	 */
	@Override
	public List<Address> getPrimaryAddress(Account account, Boolean isPrimary
			, Boolean isActive, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Address> query = builder.createQuery(Address.class);
		Root<SimpleShipping> root = query.from(SimpleShipping.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(SimpleShipping_.account), account));
		if(isPrimary != null) {
			predicates.add(builder.equal(root.get(SimpleShipping_.primary), isPrimary));
		}
		if(isActive != null) {
			predicates.add(builder.equal(root.get(SimpleShipping_.active), isActive));
		}
		
		query.select(root.get(SimpleShipping_.address))
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root.get(SimpleShipping_.primary))
					, builder.desc(root));
	
		if(pageable == null)  {
			return entityManager.createQuery(query).getResultList();
		}
		
		return entityManager.createQuery(query)
											.setFirstResult(pageable.getOffset())
											.setMaxResults(pageable.getPageSize())
											.getResultList();
		
	}

}
