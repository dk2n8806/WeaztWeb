package com.entity.payout;

import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.core.service.payout.MerchantFeeService;

public class TestMerchantFee extends BaseTest{

	@Autowired private MerchantFeeService merchantFeeService;
}
