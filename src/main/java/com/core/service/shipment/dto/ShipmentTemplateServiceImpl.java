package com.core.service.shipment.dto;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.shipment.RecurrentSubscriptionShipmentTemplateDTO;
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.core.dao.shipment.dto.ShipmentTemplateDao;

@Service
@Transactional(readOnly=true)
public class ShipmentTemplateServiceImpl 
implements LastSubscriptionShipmentTemplateDTOService, NextShipmentTemplateService{

	@Autowired private ShipmentTemplateDao shipmentTemplateDao;
	
	
	
	
		
	/** :::
	 * <p>Retrieve a list of history shipment</p>
	 * {@link LastSubscriptionShipmentTemplateDTOService#getHistoryShipments(Account, Subscription, Pageable)}
	 * ::: */
	@Override
	public List<LastSubscriptionShipmentTemplateDTO> getHistoryShipments(Account account,
			Subscription subscription, Pageable pageable) {
		return shipmentTemplateDao.getHistoryShipments(account, subscription, pageable);
	}

	
		
		
	/**
	 * {@link LastSubscriptionShipmentTemplateDTOService#getHistoryShipmentsByAccount(Account, Pageable)}
	 */
	@Override
	public List<LastSubscriptionShipmentTemplateDTO> getHistoryShipmentsByAccount(
			Account account, Pageable pageable) {
		if(account == null) {
			return new ArrayList<>();
		}
		return shipmentTemplateDao.getHistoryShipments(account, null, pageable);
	}

	
/**
 * {@link LastSubscriptionShipmentTemplateDTOService#getHistoryShipmentsBySubscription(Subscription, Pageable)}
 */
	@Override
	public List<LastSubscriptionShipmentTemplateDTO> getHistoryShipmentsBySubscription(
			Subscription subscription, Pageable pageable) {
		if(subscription == null) {
			return new ArrayList<>();
		}
		return shipmentTemplateDao.getHistoryShipments(null, subscription, pageable);
	}

	
	
	
	
/**
 * {@link NextShipmentTemplateService#getTemplates(Account, Subscription, Pageable)}
 */
	@Override
	public List<RecurrentSubscriptionShipmentTemplateDTO> getTemplates(Pageable pageable) {
		return shipmentTemplateDao.getNextShipments(null, null, pageable);
	}

	
	
	
/**
 * {@link NextShipmentTemplateService#getByAccount(Account, Pageable)}
 */
	@Override
	public List<RecurrentSubscriptionShipmentTemplateDTO> getByAccount(
			Account account, Pageable pageable) {
		if(account == null) {
			return new ArrayList<>();
		}
		return shipmentTemplateDao.getNextShipments(account, null, pageable);
	}

	
	
/**
 * {@link NextShipmentTemplateService#getBySubscription(Subscription, Pageable)}
 */
	@Override
	public List<RecurrentSubscriptionShipmentTemplateDTO> getBySubscription(
			Subscription subscription, Pageable pageable) {
		if(subscription == null) {
			return new ArrayList<>();
		}
		return shipmentTemplateDao.getNextShipments(null, subscription, pageable);
	}



	
	


}
