package com.core.dao.subscriber;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.product.Product_;
import com.common.entity.subscriber.Bookmark;
import com.common.entity.subscriber.Bookmark_;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class BookmarkDaoImpl extends GenericJpaRepository<Bookmark, Long> implements BookmarkDao {

	@Override
	public Bookmark getByAccount(Long productId, Account account) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bookmark> query = builder.createQuery(Bookmark.class);
		Root<Bookmark> root = query.from(Bookmark.class);
		
		query.select(root)
				.where(builder.equal(root.get(Bookmark_.product).get(Product_.id), productId)
						, builder.equal(root.get(Bookmark_.account), account));
		return entityManager.createQuery(query).getSingleResult();
	}

}
