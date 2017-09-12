package com.core.service.order.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.common.entity.address.Address;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.RecommendedLabel;
import com.common.entity.order.Shipment;
import com.common.type.ShipmentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.order.ShipmentDao;
import com.core.service.order.ShipmentService;

@Service
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

	@Autowired private ShipmentDao shipmentDao;
	
	@Override
	public Shipment findById(Long id) {
		return shipmentDao.findById(id);
	}

	
	@Override
	public long getRowCount() {
		return shipmentDao.getRowCount();
	}


	@Override
	public Shipment getReference(Long id) {
		return shipmentDao.getReference(id);
	}
	
	@Override
	public void update(Shipment shipment) {
		if(shipment != null)
			shipmentDao.update(shipment);
	}

	
	
/*************************************************************
 * Update status of a shipment
 * 
 * {@link ShipmentService#updateStatus(Shipment, ShipmentStatus)}
 */
	@Override
	public void updateStatus(Shipment shipment, ShipmentStatus status) {
		if(shipment != null) {
			shipmentDao.updateStatus(shipment, status);
			shipment.setStatus(status);
		}
	}

	
/***************************************************
 * Update status of shipment
 * 
 * {@link ShipmentService#markAsShipped(Shipment)}
 */
	@Override
	public void markAsShipped(Shipment shipment) {
		this.updateStatus(shipment, ShipmentStatus.SHIPPED);
	}
	
	
	
	
/************************************
 * Lookup a shipment by merchant
 * 
 * {@link ShipmentService#getByMerchant(Long, Merchant)}
 */
	@Override
	public Shipment getByMerchant(Long shipmentId, Merchant merchant) {
		if(shipmentId == null || merchant == null) {
			return null;
		}
		try {
			return shipmentDao.getByMerchant(shipmentId, merchant);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	
/****************
 * Get list of shipment
 * 
 * {@link ShipmentService#getShipments(ShipmentStatus, DateInterval, Pageable)}
 */
	@Override
	public List<Shipment> getShipments(ShipmentStatus status,
			DateInterval dateInterval, Pageable pageable) {
		return shipmentDao.getShipments(status, dateInterval, pageable);
	}
	
	
/****************
 * Count total Shipment
 * 
 * {@link ShipmentService#countShipment(ShipmentStatus, DateInterval)}
 */
	@Override
	public long countShipment(ShipmentStatus status, DateInterval dateInterval) {
		try {
			return shipmentDao.countShipment(status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	public void flush() {
		shipmentDao.flush();
	}



}
