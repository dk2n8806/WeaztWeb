package com.dto.subscription;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.service.subscription.dto.SubscriptionTemplateDTOService;

public class TestGetSubscriptionTemplates extends BaseTest{

	@Autowired private SubscriptionTemplateDTOService templateService;
	private SubscriptionStatus status;
	private DateInterval dateInterval;
	private Pageable pageable;
	
	@Test
	public void test() {
		List<SubscriptionTemplateDTO> templates = templateService.getTemplates(status, dateInterval, pageable);

		System.out.println(templates.size());
	}
}
