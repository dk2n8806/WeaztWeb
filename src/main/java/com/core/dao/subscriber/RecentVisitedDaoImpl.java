package com.core.dao.subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.subscriber.RecentVisited;
import com.common.entity.subscriber.RecentVisited_;
import com.common.type.ProductStatus;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class RecentVisitedDaoImpl 
	extends GenericJpaRepository<RecentVisited, Long>
implements RecentVisitedDao
{

	@Override
	public RecentVisited getRecentVisited(Account account, Product product) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecentVisited> query = builder.createQuery(RecentVisited.class);
		Root<RecentVisited> root = query.from(RecentVisited.class);
		
		query.select(root)
			.where(builder.equal(root.get(RecentVisited_.account), account)
					, builder.equal(root.get(RecentVisited_.product), product));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	/** 
	 * {@link RecentVisitedDao#count}
	 */
	@Override
	public long count(Account account, ProductStatus status) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<RecentVisited> root = query.from(RecentVisited.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(root.get(RecentVisited_.account), account));
		if(status != null) 
			predicates.add(builder.equal(root.get(RecentVisited_.product).get(Product_.status), status));
			
		
		query.select(builder.count(root))
				.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

}
