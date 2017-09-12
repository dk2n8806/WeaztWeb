package com.core.dao.subscriber;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.RecentVisited;
import com.common.type.ProductStatus;
import com.core.dao.generic.GenericRepository;

public interface RecentVisitedDao extends GenericRepository<RecentVisited, Long>{

	RecentVisited getRecentVisited(Account account, Product product);
	
	/** {@link RecentVisitedDaoImpl#count}*/
	long count(Account account, ProductStatus status);
}
