package com.core.service.address.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.address.Address;
import com.core.dao.address.AddressDao;
import com.core.service.address.AddressService;


/**
 * 
 * @author dk2n_
 *
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired private AddressDao addressDao;
	
	@Override
	public Address findById(Long id) {
		return addressDao.findById(id);
	}

	@Override
	public Address getReference(Long id) {
		return addressDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return addressDao.getRowCount();
	}

}
