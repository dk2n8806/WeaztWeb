package com.core.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.account.Account_;
import com.common.entity.account.Profile;
import com.common.entity.merchant.Merchant;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.account.AccountDao;
import com.core.dao.generic.GenericJpaRepository;

/**
 * An Implementation of {@link AccountDao} 
 * 
 * Provide an accessible way to account entities
 * @author dk2n_
 *
 */
@Repository
public class AccountDaoImpl 
	extends GenericJpaRepository<Account, Long>
	implements AccountDao
{


/** :::
 * <p>Retrieve an account entity by a given email</p>
 * 
 * @query
 * [
 * 		Select A From {@link Account} A
 * 		Where A.email = :email
 * ]
 * 
 * {@link AccountDao#getByEmail(String)}
 * ::: */
	@Override
	public Account getByEmail(String email) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> rootAccount = query.from(Account.class);
		query.select(rootAccount)
				.where(builder.equal(rootAccount.get(Account_.email), email));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
/** :::
 * <p>Retrieve a merchant entity by an account</p>
 * @query
 * [
 * 		Select M From {@link Account} A
 * 			Join A.merchant M
 * 		Where A = :account 
 * ]
 * 
 * 
 * {@link AccountDao#getMerchant(Account)}
 */
	@Override
	public Merchant getMerchant(Account customer) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> query = builder.createQuery(Merchant.class);
		Root<Account> rootAccount = query.from(Account.class);
		Join<Account, Merchant> rootMerchant = 
						rootAccount.join(Account_.merchant);
		query.select(rootMerchant)
				.where(builder.equal(rootAccount, customer));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	
	
	
/** :::
 * <p>Retrieve a profile entity by a given account</p>
 * 
 * @query
 * [
 * 		Select P From {@link Account} A
 * 			Join A.profile P
 * 		Where A = :account 
 * ]
 * 
 * {@link AccountDao#getProfile(Account)}
 * ::: */
	@Override
	public Profile getProfile(Account customer) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Profile> query = builder.createQuery(Profile.class);
		Root<Account> rootAccount = query.from(Account.class);
		Join<Account, Profile> joinProfile
						= rootAccount.join(Account_.profile);
		query.select(joinProfile)
			.where(builder.equal(rootAccount, customer));
		return entityManager.createQuery(query).getSingleResult();
	}


	
/** :::
 * <p>Retrieve a list of account entities</p>
 * 
 * @query
 * [
 * 		Select A From {@link Account} A
 * 		Where A.role = :role
 * 			And A.status = :status
 * 			And A.joinedOn between :from And :to
 * 		Order By A DESC
 * ]
 * 
 * {@link AccountDao#getAccounts(Role, AccountStatus, DateInterval, Pageable)}
 * ::: */
	@Override
	public List<Account> getAccounts(Role role, AccountStatus status
			, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(role != null) {
			predicates.add(builder.equal(root.get(Account_.role), role));
		}
		if(status != null) {
			predicates.add(builder.equal(root.get(Account_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Account_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return this.getResultList(query, pageable);
	}
	
	
	
	
/** :::
 * Count total account entities
 * @query
 * [
 * 		Select count(A) From {@link Account} A
 * 		Where A.role = :role
 * 			And A.status = :status
 * 			And A.joinedOn between :from And :to
 * ]
 * 
 * {@link AccountDao#countAccount(Role, AccountStatus, DateInterval)}
 * ::: */
	@Override
	public long countAccount(Role role, AccountStatus status, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Account> root = query.from(Account.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(role != null) {
			predicates.add(builder.equal(root.get(Account_.role), role));
		}
		if(status != null) {
			predicates.add(builder.equal(root.get(Account_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(Account_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(query).getSingleResult();
	}

	
/** :::
 * <p>Update an account_status</p>
 * 
 * @query
 * [
 * 		Update {@link Account} ACC
 * 		Set ACC.status = :status
 * 		Where ACC = :customer
 * ]
 * 
 * {@link AccountDao#updateStatus(Account, AccountStatus)}
 * ::: */
	@Override
	public void updateStatus(Account customer, AccountStatus status) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Account> query = builder.createCriteriaUpdate(Account.class);
		Root<Account> root = query.from(Account.class);
		
		query.set(root.get(Account_.status), status)
			.where(builder.equal(root, customer));
		
		entityManager.createQuery(query).getSingleResult();
	}


	
	
/** :::
 * <p>Update an account_role</p>
 * 
 * @query
 * [
 * 		Update {@link Account} ACC
 * 		Set ACC.status = :status
 * 		Where ACC = :customer
 * ]
 * 
 * {@link AccountDao#updateRole(Account, Role)}
 * ::: */
	@Override
	public void updateRole(Account customer, Role role) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Account> query = builder.createCriteriaUpdate(Account.class);
		Root<Account> root = query.from(Account.class);
		
		query.set(root.get(Account_.role), role)
			.where(builder.equal(root, customer));
		
		entityManager.createQuery(query).executeUpdate();
	}



}
