package com.core.dao.merchant.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.Merchant_;
import com.common.entity.merchant.TaxRate;
import com.common.entity.product.Product;
import com.common.type.MerchantStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.merchant.MerchantDao;

@Repository
public class MerchantDaoImpl 
	extends GenericJpaRepository<Merchant, Long>
	implements MerchantDao
{
	
	/** :::
	 * <p>Lookup a merchant entity by a given account</p>
	 * @query
	 * [
	 * 		Select M From {@link Merchant} M
	 * 		Where M.account = :account
	 * ]
	 * 
	 * {@link MerchantDao#getByAccount(Account)}
	 * ::: */
	@Override
	public Merchant getByAccount(Account account) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> query = builder.createQuery(Merchant.class);
		Root<Merchant> rootMerchant = query.from(Merchant.class);
		
		query.select(rootMerchant)
			.where(builder.equal(rootMerchant.get(Merchant_.account), account));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	/** :::
	 * <p>Retrieve a commission rate of the merchant</p>
	 * 
	 * @query
	 * [
	 * 		Select M.rate From {@link Merchant} M
	 * 		Where m = :merchant
	 * ]
	 * 
	 * {@link MerchantDao#getCommissionRate(Merchant)}
	 * ::: */
	@Override
	public CommissionRate getCommissionRate(Merchant merchant) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CommissionRate> query = builder.createQuery(CommissionRate.class);
		Root<Merchant> rootMerchant = query.from(Merchant.class);
		
		query.select(rootMerchant.get(Merchant_.commissionRate))
			.where(builder.equal(rootMerchant, merchant));
		return entityManager.createQuery(query).getSingleResult();
	}

		
	
	/** :::
	 * @query
	 * [
	 * 		Select M.contact From {@link Merchant} M
	 * 		Where M.id = :merchantId
	 * ]
	 * 
	 * {@link MerchantDao#getContact(Long)}
	 * ::: */
	@Override
	public MerchantProfile getProfile(Merchant merchant) {
		final CriteriaBuilder build = entityManager.getCriteriaBuilder();
		CriteriaQuery<MerchantProfile> query = build.createQuery(MerchantProfile.class);
		Root<Merchant> root = query.from(Merchant.class);
		query.select(root.get(Merchant_.profile))	
				.where(build.equal(root, merchant));
		return entityManager.createQuery(query).getSingleResult();
	}



	
	/** :::
	 * <p>Update status column of a merchant entity</p>
	 * 
	 * @query
	 * [
	 * 		Update {@link Merchant} M
	 * 		Set M.status = :status
	 * 		Where M.id = :merchantId
	 * ]
	 * 
	 * {@link MerchantDao#updateStatus(Long, MerchantStatus)}
	 * ::: */
	@Override
	public void updateStatus(Long merchantId, MerchantStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Merchant> query = builder.createCriteriaUpdate(Merchant.class);
		Root<Merchant> root = query.from(Merchant.class);
		
		query.set(Merchant_.status, status)
			.where(builder.equal(root.get(Merchant_.id), merchantId));
		entityManager.createQuery(query).executeUpdate();
	}



	
	/** :::
	 * <p>Retrieve an account entity associating with the merchant</p>
	 * 
	 * @query
	 * [
	 * 		Select M.account From {@link Merchant} M
	 * 		Where M.id = :merchantId
	 * ]	
	 * 
	 * {@link MerchantDao#getAccount(Long)}
	 * ::: */
	@Override
	public Account getAccount(Merchant merchant) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Merchant> root = query.from(Merchant.class);
		
		query.select(root.get(Merchant_.account))
		 	 .where(builder.equal(root, merchant));
		
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	
	
	/** :::
	 * <p>Retrieve a list of merchant entities</p>
	 * 
	 * @query
	 * [
	 * 		Select M From {@link Merchant} M
	 * 		Where M.status = :status
	 * 			And M.createdOn Between :from And :to
	 * 		Order By M DESC
	 * ]
	 * 
	 * {@link MerchantDao#getMerchants(MerchantStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Merchant> getMerchants(MerchantStatus status,
			DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> query = builder.createQuery(Merchant.class);
		Root<Merchant> root = query.from(Merchant.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Merchant_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Merchant_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[predicates.size()]))
			.orderBy(builder.desc(root));
		
		return this.getResultList(query, pageable);
	}
	
	
	
	
	
	/** :::
	 * <p>Count total merchant entities</p>
	 * 
	 * @query
	 * [
	 * 		Select count(M) From {@link Merchant} M
	 * 		Where M.status = :status
	 * 			And M.createdOn Between :from And :to
	 * ]
	 * 
	 * {@link MerchantDao#countMerchant(MerchantStatus, DateInterval)}
	 * ::: */
	@Override
	public long countMerchants(MerchantStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Merchant> root = query.from(Merchant.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(status != null) {
			predicates.add(builder.equal(root.get(Merchant_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Merchant_.createdOn)
								, dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult();
	}



	
	/** :::
	 * 
	 * @query
	 * [
	 * 		Select M.taxRate From {@link Merchant} M
	 * 		Where M = :merchant
	 * ]
	 * ::: */
	@Override
	public TaxRate getTaxRate(Merchant merchant) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TaxRate> query = builder.createQuery(TaxRate.class);
		Root<Merchant> rootMerchant = query.from(Merchant.class);
		
		query.select(rootMerchant.get(Merchant_.taxRate))
			.where(builder.equal(rootMerchant, merchant));
		return entityManager.createQuery(query).getSingleResult();
	}



		
}
