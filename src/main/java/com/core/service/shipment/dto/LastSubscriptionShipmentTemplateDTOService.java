package com.core.service.shipment.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;

public interface LastSubscriptionShipmentTemplateDTOService {


	/** {@link ShipmentTemplateServiceImpl#getHistoryShipments(Account, Subscription, Pageable)} */
	List<LastSubscriptionShipmentTemplateDTO> getHistoryShipments(Account account
			, Subscription subscription, Pageable pageable);

	/** {@link ShipmentTemplateServiceImpl#getHistoryShipmentsByAccount(Account, Pageable)} */
	List<LastSubscriptionShipmentTemplateDTO> getHistoryShipmentsByAccount(
					Account account, Pageable pageable);

	/** {@link ShipmentTemplateServiceImpl#getHistoryShipmentsBySubscription(Subscription, Pageable)} */
	List<LastSubscriptionShipmentTemplateDTO> getHistoryShipmentsBySubscription(
					Subscription subscription, Pageable pageable);

	
	

	
}
