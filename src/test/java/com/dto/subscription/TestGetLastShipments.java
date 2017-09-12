package com.dto.subscription;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.core.service.shipment.dto.LastSubscriptionShipmentTemplateDTOService;

public class TestGetLastShipments extends BaseTest {

	@Autowired private LastSubscriptionShipmentTemplateDTOService templateDTOService;
	
	@Test
	public void test() {
		List<LastSubscriptionShipmentTemplateDTO> subscriptions 
								= templateDTOService.getHistoryShipments(null, null, null);
		
		System.out.println(subscriptions.size());
		for(LastSubscriptionShipmentTemplateDTO t : subscriptions) {
			System.out.println(t);
		}
	}
}
