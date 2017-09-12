package com.core.service.product.wrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.product.Product;
import com.common.wrapper.ProductSubscriptionWrapper;
import com.core.dao.product.wrapper.ProductSubscriptionWrapperDao;

@Service
@Transactional
public class ProductSubscriptionWrapperServiceImpl implements ProductWrapperService {

	@Autowired private ProductSubscriptionWrapperDao dao;
	
	@Override
	public List<ProductSubscriptionWrapper> getByProduct(Product product) {
		return dao.getSubscription(product);
	}

}
