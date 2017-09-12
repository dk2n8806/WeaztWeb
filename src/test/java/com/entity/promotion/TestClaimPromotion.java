package com.entity.promotion;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.Promotion;
import com.core.service.account.AccountService;
import com.core.service.promo.ClaimedPromotionService;
import com.core.service.promo.PromotionService;

public class TestClaimPromotion extends BaseTest {

	@Autowired private AccountService accountService;
	@Autowired private PromotionService promotionService;
	@Autowired private ClaimedPromotionService claimedPromotionService;
	
	
	@Test
	public void test() {
		Account account = accountService.findById(new Long(2132007));
		Promotion promotion = promotionService.findById(new Long(2132340));
		ClaimedPromotion claim = claimedPromotionService.save(account, promotion);
		
		System.out.println(claim.getId());
	}
}
