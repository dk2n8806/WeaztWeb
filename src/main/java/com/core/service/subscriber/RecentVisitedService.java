package com.core.service.subscriber;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.RecentVisited;
import com.common.type.ProductStatus;

public interface RecentVisitedService {

	RecentVisited save(Account account, Product product);
	
	RecentVisited getRecentVisited(Account account, Product product);
	
	void deactive(Account account, Product product);
	
	/** {@link RecentVisitedServiceImpl#countByAccount} */
	long countByAccount(Account account, ProductStatus status);
}
