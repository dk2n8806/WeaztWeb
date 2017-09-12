package com.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.AbstractPersistenceToken;
import com.common.entity.AbstractPersistenceToken_;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class AbstractTokenDaoImpl 
extends GenericJpaRepository<AbstractPersistenceToken, Long> 
implements AbstractTokenDao {

	
/****************************************************
 * Update status a list of token
 * 
 * @query
 * [
 * 		Update {@link AbstractPersistenceToken} TOKEN
 * 		Set TOKEN.useable = :isUseable
 * 		Where (or) TOKEN = :token
 * ]
 * 
 * {@link AbstractTokenDao#updateStatus(List, boolean)}
 */
	@Override
	public void updateStatus(List<? extends AbstractPersistenceToken> tokens, boolean isUseable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<AbstractPersistenceToken> query
						= builder.createCriteriaUpdate(AbstractPersistenceToken.class);
		Root<AbstractPersistenceToken> root 
						= query.from(AbstractPersistenceToken.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		for(AbstractPersistenceToken token : tokens) {
			predicates.add(builder.equal(root, token));
		}
		query.set(root.get(AbstractPersistenceToken_.useable), isUseable)
			.where(builder.or(predicates.toArray(new Predicate[]{})));
		
		entityManager.createQuery(query).executeUpdate();
	}
	
	
	
/*********************************************
 * Lookup by id
 * 
 * {@link AbstractTokenDao#getById(Long)}
 */
	@Override
	public AbstractPersistenceToken getById(Long tokenId) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AbstractPersistenceToken> query = builder.createQuery(AbstractPersistenceToken.class);

		Root<AbstractPersistenceToken> root 
						= query.from(AbstractPersistenceToken.class);
		
		query.select(root)
			.where(builder.equal(root.get(AbstractPersistenceToken_.id), tokenId));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
