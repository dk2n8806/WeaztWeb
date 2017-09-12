package com.entity.payout;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.entity.payout.Payout;
import com.common.type.PayoutStatus;
import com.common.util.date.DateInterval;
import com.core.service.merchant.MerchantService;
import com.core.service.payout.PayoutService;

public class TestPayout extends BaseTest{

	@Autowired private PayoutService payoutService;
	@Autowired private MerchantService merchantService;
	
	
	@Test
	public void test() {
		DateInterval dateInterval = null;
		Pageable pageable = null;
		PayoutStatus status = null;
		List<Payout> payouts = payoutService.getPayouts(status , dateInterval, pageable);
		System.out.println("size: " + payouts.size());
		System.out.println("amount: " + payoutService.getTotalAmount(status, dateInterval));
	}
	
}
