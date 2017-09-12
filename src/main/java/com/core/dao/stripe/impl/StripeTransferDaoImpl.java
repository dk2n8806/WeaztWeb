package com.core.dao.stripe.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.adapter.stripe.TransferAdapter_;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeAccount_;
import com.common.entity.stripe.StripeTransfer;
import com.common.entity.stripe.StripeTransfer_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.stripe.StripeTransferDao;

/************************************************************************
 * 
 * @author dk
 *
 */
@Repository
public class StripeTransferDaoImpl extends GenericJpaRepository<StripeTransfer, Long>
implements StripeTransferDao {

	
	


/****************************************************************
 * @query
 * [
 * 		Select ST 
 * 		From {@link StripeTransfer} ST
 * 		Where ST.tokenId = :customerAdapter
 * ]
 * 
 * {@link StripeTransferDao#getByToken(String)}
 */
	@Override
	public StripeTransfer getByToken(String tokenId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);
		query.select(root)
			.where(builder.equal(root.get(StripeTransfer_.transferAdapter)
									 .get(TransferAdapter_.tokenId), tokenId));
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select TRANSFER 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount = :stripeAccount
 * 			And TRANSFER.id = :transferId
 * ]
 * 
 * {@link StripeTransferDao#getByStripeAccount(StripeAccount, Long)}
 */
	@Override
	public StripeTransfer getByStripeAccount(StripeAccount stripeAccount,
			Long transferId) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);
	
		query.select(root)
				.where(builder.equal(root.get(StripeTransfer_.stripeAccount), stripeAccount)
					, builder.equal(root.get(StripeTransfer_.id), transferId));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select TRANSFER 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount.merchant = :merchant
 * 			And TRANSFER.id = :transferId
 * ]
 * 
 * {@link StripeTransferDaoImpl#getByMerchant(Merchant, Long)}
 */
	@Override
	public StripeTransfer getByMerchant(Merchant merchant, Long transferId) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);
	
		query.select(root)
				.where(builder.equal(root.get(StripeTransfer_.stripeAccount)
										.get(StripeAccount_.merchant), merchant)
					, builder.equal(root.get(StripeTransfer_.id), transferId));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select TRANSFER 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.createdOn Between :dateInterval.from 
 * 									 And :dateInterval.to
 * 		Order By TRANSFER.id DESC
 * ]
 * 
 * {@link StripeTransferDao#getList(DateInterval, Pageable)}
 */
	@Override
	public List<StripeTransfer> getList(DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(dateInterval != null) {
			// TRANSFER.createdOn Bettwen :dateInterval.from And :dateInteval.to
			predicates.add(builder.between(root.get(StripeTransfer_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		query.select(root)
			.where(predicates.toArray(new Predicate[predicates.size()]))
		 	.orderBy(builder.desc(root.get(StripeTransfer_.id)));
		return getResultList(query, pageable);
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select TRANSFER 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount = :stripeAccount
 * 			And TRANSFER.createdOn Between :dateInterval.from 
 * 									   And :dateInterval.to
 * 		Order By TRANSFER.id DESC
 * ]
 * 
 * {@link StripeTransferDao#getListByStripeAccount(StripeAccount, DateInterval, Pageable)}
 */
	@Override
	public List<StripeTransfer> getListByStripeAccount(StripeAccount stripeAccount
			, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// TRANSFER.stripeAccount = :stripeAccount
		predicates.add(builder.equal(root.get(StripeTransfer_.stripeAccount), stripeAccount));
		if(dateInterval != null) {
			// TRANSFER.createdOn Bettwen :dateInterval.from And :dateInteval.to
			predicates.add(builder.between(root.get(StripeTransfer_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(root)
		.where(predicates.toArray(new Predicate[predicates.size()]))
			.orderBy(builder.desc(root.get(StripeTransfer_.id)));
		return getResultList(query, pageable);
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select TRANSFER 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount.merchant = :merchant
 * 			And TRANSFER.createdOn Between :dateInterval.from 
 * 									   And :dateInterval.to
 * 		Order By TRANSFER.id DESC
 * ]
 * 
 * {@link StripeTransferDao#getListByMerchant(Merchant, DateInterval,  Pageable)}
 */
	@Override
	public List<StripeTransfer> getListByMerchant(Merchant merchant
			, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StripeTransfer> query = builder.createQuery(StripeTransfer.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);
		
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		// TRANSFER.stripeAccount.merchant = :stripeAccount.merchant
		predicates.add(builder.equal(root.get(StripeTransfer_.stripeAccount)
				 						 .get(StripeAccount_.merchant), merchant));
		if(dateInterval != null) {
			// TRANSFER.createdOn Bettwen :dateInterval.from And :dateInteval.to
			predicates.add(builder.between(root.get(StripeTransfer_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		// TRANSFER.stripeAccount.merchant = :merchant
		query.select(root)
			.where(predicates.toArray(new Predicate[predicates.size()]))
			.orderBy(builder.desc(root.get(StripeTransfer_.id)));
		return getResultList(query, pageable);
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select count(TRANSFER) 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount = :stripeAccount
 * 			And TRANSFER.createdOn Between :dateInterval.from 
 * 									   And :dateInterval.to
 * 		Order By TRANSFER.id DESC
 * ]
 * 
 * {@link StripeTransferDao#countByStripeAccount(StripeAccount, DateInterval)}
 */
	@Override
	public long countByStripeAccount(StripeAccount stripeAccount, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// TRANSFER.stripeAccount = :stripeAccount
		predicates.add(builder.equal(root.get(StripeTransfer_.stripeAccount), stripeAccount));
		if(dateInterval != null) {
			// TRANSFER.createdOn Bettwen :dateInterval.from And :dateInteval.to
			predicates.add(builder.between(root.get(StripeTransfer_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	
/***********************************************************************
 * @query
 * [
 * 		Select count(TRANSFER) 
 * 		From {@link StripeTransfer} TRANSFER
 * 		Where TRANSFER.stripeAccount.merchant = :merchant
 * 			And TRANSFER.createdOn Between :dateInterval.from 
 * 									   And :dateInterval.to
 * 		Order By TRANSFER.id DESC
 * ]
 * 
 * {@link StripeTransferDao#countByMerchant(Merchant, DateInterval)}
 */
	@Override
	public long countByMerchant(Merchant merchant, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<StripeTransfer> root = query.from(StripeTransfer.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// TRANSFER.stripeAccount.merchant = :stripeAccount.merchant
		predicates.add(builder.equal(root.get(StripeTransfer_.stripeAccount)
				 						 .get(StripeAccount_.merchant), merchant));
		if(dateInterval != null) {
			// TRANSFER.createdOn Bettwen :dateInterval.from And :dateInteval.to
			predicates.add(builder.between(root.get(StripeTransfer_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}


	
	

	
	


	
	




}
