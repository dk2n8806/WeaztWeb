package com.core.dao.shipment.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.shipment.RecurrentSubscriptionShipmentTemplateDTO;
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;

public interface ShipmentTemplateDao {

	/** {@link ShipmentTemplateDaoImpl#getHistoryShipments(Account, Subscription, Pageable)} */
	List<LastSubscriptionShipmentTemplateDTO> getHistoryShipments(Account account
										, Subscription subscription, Pageable pageable);
	
	/** {@link ShipmentTemplateDaoImpl#getNextShipments(Account, Subscription, Pageable)} */
	List<RecurrentSubscriptionShipmentTemplateDTO> getNextShipments(Account account
										, Subscription subscription, Pageable pageable);

}
