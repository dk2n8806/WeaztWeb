package com.dto.product;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.wrapper.ProductSubscriptionWrapper;
import com.core.dao.product.wrapper.ProductSubscriptionWrapperDao;
import com.core.service.product.wrapper.ProductWrapperService;

public class TestGetProductWrapper extends BaseTest{

	@Autowired private ProductWrapperService service;
	
	@Test
	public void test() {
		List<ProductSubscriptionWrapper> list = service.getByProduct(null);
		
		for(ProductSubscriptionWrapper w : list) {
			System.out.println(w.getProduct().getId() + "\n\tcount: " + w.getTotalSubscription() );
		}
	}
}
