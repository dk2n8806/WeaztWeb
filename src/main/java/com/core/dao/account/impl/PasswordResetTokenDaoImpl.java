package com.core.dao.account.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.account.PasswordResetToken;
import com.common.entity.account.PasswordResetToken_;
import com.core.dao.account.PasswordResetTokenDao;
import com.core.dao.generic.GenericJpaRepository;


/**
 * 
 * @author dk2n_
 *
 */
@Repository
public class PasswordResetTokenDaoImpl extends GenericJpaRepository<PasswordResetToken, Long>
implements PasswordResetTokenDao {

	
	/** :::
	 * <p>Lookup a password reset token</p>
	 * 
	 * @query
	 * [
	 * 		Select TOKEN From {@link PasswordResetToken} TOKEN
	 * 		Where TOKEN.token = :token
	 * ]
	 * 
	 * {@link PasswordResetTokenDao#getByToken(String)}
	 */
	@Override
	public PasswordResetToken getByToken(String token) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PasswordResetToken> query = builder.createQuery(PasswordResetToken.class);
		Root<PasswordResetToken> root = query.from(PasswordResetToken.class);
		query.select(root)
				.where(builder.equal(root.get(PasswordResetToken_.token), token));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	/** :::
	 * <p>Toggle by account</p>
	 * 
	 * {@link PasswordResetTokenDao#toggleUseableByAccount(boolean, Account)}
	 * ::: */
	@Override
	public void toggleUseableByAccount(boolean useable, Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<PasswordResetToken> query = 
				builder.createCriteriaUpdate(PasswordResetToken.class);
		Root<PasswordResetToken> root = query.from(PasswordResetToken.class);
		query.set(root.get(PasswordResetToken_.useable), useable)
			.where(builder.equal(root.get(PasswordResetToken_.account), account));
		entityManager.createQuery(query).executeUpdate();
		
	}
	
	
	
	
	/** :::
	 * 
	 * {@link PasswordResetTokenDao#getByAccount(Account)}
	 * ::: */
	@Override
	public List<PasswordResetToken> getByAccount(Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PasswordResetToken> query 
				= builder.createQuery(PasswordResetToken.class);
		Root<PasswordResetToken> root = query.from(PasswordResetToken.class);
		
		query.select(root)
			.where(builder.equal(root.get(PasswordResetToken_.account), account))
			.orderBy(builder.desc(root));
		
		return entityManager.createQuery(query).getResultList();
	}

}
