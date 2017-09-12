package com.core.dao.product.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeleteProductToken;
import com.common.entity.product.DeleteProductToken_;
import com.common.entity.product.Product_;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.product.DeleteProductTokenDao;

@Repository
public class DeleteProductTokenDaoImpl 
extends GenericJpaRepository<DeleteProductToken, Long>
implements DeleteProductTokenDao {

	
	
	
/*************************************************************************
 * Retrieve a delete_token by a token_id 
 * 
 * @query
 * [
 * 		Select TOKEN
 * 		From {@link DeleteProductToken} TOKEN
 * 		Where TOKEN.product.merchant = :merchant
 * 			And TOKEN.token = :token
 * ]
 * 
 * {@link DeleteProductTokenDao#getByMerchant(Merchant, String)}
 */
	@Override
	public DeleteProductToken getByMerchant(Merchant merchant, String token) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeleteProductToken> query = builder.createQuery(DeleteProductToken.class);
		Root<DeleteProductToken> root = query.from(DeleteProductToken.class);
		
		query.select(root)
			.where(builder.equal(root.get(DeleteProductToken_.token), token)
					, builder.equal(root.get(DeleteProductToken_.product)
										.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}

	
/*************************************************************************
 * Retrieve a list of delete_token by merchant
 * 
 * @query
 * [
 * 		Select TOKEN
 * 		From {@link DeleteProductToken} TOKEN
 * 		Where TOKEN.product.merchant = :merchant
 * 			And TOKEN.useable = :isUseable
 * 		Order By TOKEN.id DESC
 * ]
 * 
 * {@link DeleteProductTokenDao#getByMerchant(Merchant, Boolean, Pageable)}
 */
	@Override
	public List<DeleteProductToken> getByMerchant(Merchant merchant,
			Boolean isUseable, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeleteProductToken> query = builder.createQuery(DeleteProductToken.class);
		Root<DeleteProductToken> root = query.from(DeleteProductToken.class);
		
		query.select(root)
			.where(builder.equal(root.get(DeleteProductToken_.useable), isUseable)
					, builder.equal(root.get(DeleteProductToken_.product)
										.get(Product_.merchant), merchant))
			.orderBy(builder.desc(root.get(DeleteProductToken_.id)));
		return this.getResultList(query, pageable);
	}


	
/*************************************************************************
 * Retrieve a list of delete_token by merchant
 * 
 * @query
 * [
 * 		Select count(TOKEN)
 * 		From {@link DeleteProductToken} TOKEN
 * 		Where TOKEN.product.merchant = :merchant
 * 			And TOKEN.useable = :isUseable
 * ]
 * 
 * {@link DeleteProductTokenDao#countByMerchant(Merchant, Boolean)}
 */
	@Override
	public long countByMerchant(Merchant merchant, Boolean isUseable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<DeleteProductToken> root = query.from(DeleteProductToken.class);
		
		query.select(builder.count(root))
			.where(builder.equal(root.get(DeleteProductToken_.useable), isUseable)
					, builder.equal(root.get(DeleteProductToken_.product)
										.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}

}
