package com.entity.promotion;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.promo.Promotion;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.promo.PromotionService;

public class TestCreatePromotion extends BaseTest{

	@Autowired private PromotionService promotionService;
	
	@Test
	public void test() {
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), 0);
		dateInterval.setInterval(dateInterval.getFrom(), 10);
		Promotion promotion1 = promotionService.save("WEZNOW10", 1000, dateInterval.getFrom(), dateInterval.getTo());
		
		System.out.println(promotion1);
	}
	
}
