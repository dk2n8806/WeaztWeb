package com.core.dao.product.wrapper;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.wrapper.ProductSubscriptionWrapper;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class ProductSubscriptionWrapperDaoImpl 
	extends GenericJpaRepository<ProductSubscriptionWrapper, Long>
implements ProductSubscriptionWrapperDao
{

	@Override
	public List<ProductSubscriptionWrapper> getSubscription(Product product) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductSubscriptionWrapper> query
					= builder.createQuery(ProductSubscriptionWrapper.class);
		Root<Subscription> root = query.from(Subscription.class);
		
		query.select(builder.construct(ProductSubscriptionWrapper.class
											, root.get(Subscription_.product)
											, builder.count(root)
											)).groupBy(root.get(Subscription_.product));
		
		return getResultList(query, null);
	}

}
