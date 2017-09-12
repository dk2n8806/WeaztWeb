package com.core.dao.account.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.account.VerificationToken;
import com.common.entity.account.VerificationToken_;
import com.core.dao.account.VerificationTokenDao;
import com.core.dao.generic.GenericJpaRepository;


/**
 * 
 * @author dk2n_
 *
 */
@Repository
public class VerificationTokenDaoImpl 
extends GenericJpaRepository<VerificationToken, Long>
implements VerificationTokenDao {

	
		
		
	/** :::
	 * @query
	 * [ 
	 * 		Select V From {@link VerificationToken} V
	 * 		Where V.token = :token
	 * ]
	 * 
	 * {@link VerificationTokenService#getByToken(String)}
	 * ::: */
	@Override
	public VerificationToken getByToken(String token) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VerificationToken> query = builder.createQuery(VerificationToken.class);
		Root<VerificationToken> root = query.from(VerificationToken.class);
		
		query.select(root)
				.where(builder.equal(root.get(VerificationToken_.token), token));
		
		return entityManager.createQuery(query).getSingleResult();
	}
	
	
	

}
