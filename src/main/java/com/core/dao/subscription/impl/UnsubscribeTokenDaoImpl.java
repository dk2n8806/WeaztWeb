package com.core.dao.subscription.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription_;
import com.common.entity.subscription.UnsubscribeToken;
import com.common.entity.subscription.UnsubscribeToken_;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.subscription.UnsubscribeTokenDao;


@Repository
public class UnsubscribeTokenDaoImpl 
extends GenericJpaRepository<UnsubscribeToken, Long>
implements UnsubscribeTokenDao {

	
	
	/** :::
	 * <p>Retrieve a list of sunsubscribe_token</p>
	 * 
	 * @query
	 * [
	 * 		Select TOKEN 
	 * 		From {@link UnsubscribeToken} TOKEN
	 * 		Where TOKEN.subscription.account = :account	
	 * 			And TOKEN.useable = :isUseable
	 * 		Order By TOKEN.id DESC
	 * ]
	 * 
	 * {@link UnsubscribeTokenDao#getTokens(Account, Boolean, Pageable)}
	 */
	@Override
	public List<UnsubscribeToken> getTokens(Account account,
			Boolean isUseable, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnsubscribeToken> query = builder.createQuery(UnsubscribeToken.class);
		Root<UnsubscribeToken> root = query.from(UnsubscribeToken.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(account != null) {
			predicates.add(builder.equal(root.get(UnsubscribeToken_.subscription)
											.get(Subscription_.account), account));	
		}
		if(isUseable != null) {
			predicates.add(builder.equal(root.get(UnsubscribeToken_.useable), isUseable));
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root.get(UnsubscribeToken_.id)));
		
		return this.getResultList(query, pageable);
	}

	
		
	
	/**
	 * <p>count totalCharge sunsubscribe_token</p>
	 * 
	 * @query
	 * [
	 * 		Select count(TOKEN) 
	 * 		From {@link UnsubscribeToken} TOKEN
	 * 		Where TOKEN.subscription.account = :account	
	 * 			And TOKEN.useable = :isUseable
	 * ]
	 * 
	 * {@link UnsubscribeTokenDao#countTokens(Account, Boolean)}
	 */
	@Override
	public long countTokens(Account account, Boolean isUseable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<UnsubscribeToken> root = query.from(UnsubscribeToken.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(account != null) {
			predicates.add(builder.equal(root.get(UnsubscribeToken_.subscription)
											.get(Subscription_.account), account));	
		}
		if(isUseable != null) {
			predicates.add(builder.equal(root.get(UnsubscribeToken_.useable), isUseable));
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}


	

	
	/** :::
	 * <p>Retrieve an unsubscribe_token by a account on the tokenId</p>
	 * 
	 * @query
	 * [
	 * 		Select TOKEN 
	 * 		From {@link UnsubscribeToken} TOKEN
	 * 		Where TOKEN.subscription.account = :account	
	 * 			And TOKEN.token = :token
	 * ]
	 * 
	 * {@link UnsubscribeTokenDao#countTokens(Account, Boolean)}
	 */
	@Override
	public UnsubscribeToken getToken(Account account, String token) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnsubscribeToken> query = builder.createQuery(UnsubscribeToken.class);
		Root<UnsubscribeToken> root = query.from(UnsubscribeToken.class);
		

		query.select(root)
			.where(builder.equal(root.get(UnsubscribeToken_.subscription)
									 .get(Subscription_.account), account)
					, builder.equal(root.get(UnsubscribeToken_.token), token))
					;
		return entityManager.createQuery(query).getSingleResult();
	}

}
