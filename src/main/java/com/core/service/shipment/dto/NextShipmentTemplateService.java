package com.core.service.shipment.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.shipment.RecurrentSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;

public interface NextShipmentTemplateService {

	/** {@link ShipmentTemplateServiceImpl#getTemplates(Account, Subscription, Pageable)} */
	List<RecurrentSubscriptionShipmentTemplateDTO> getTemplates(Pageable pageable);
	
	/** {@link ShipmentTemplateServiceImpl#getByAccount(Account, Pageable)} */
	List<RecurrentSubscriptionShipmentTemplateDTO> getByAccount(
										Account account, Pageable pageable);
	
	/** {@link ShipmentTemplateServiceImpl#getBySubscription(Subscription, Pageable)} */
	List<RecurrentSubscriptionShipmentTemplateDTO> getBySubscription(
										Subscription subscription, Pageable pageable);


	
}
