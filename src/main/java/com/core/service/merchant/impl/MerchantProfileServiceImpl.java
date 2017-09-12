package com.core.service.merchant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.MerchantProfile;
import com.core.dao.merchant.MerchantProfileDao;
import com.core.service.merchant.MerchantProfileService;

@Service
@Transactional
public class MerchantProfileServiceImpl implements MerchantProfileService {

	@Autowired MerchantProfileDao merchantDao;
	
	@Override
	public MerchantProfile findById(Long id) {
		return merchantDao.findById(id);
	}

	@Override
	public MerchantProfile getReference(Long id) {
		return merchantDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return merchantDao.getRowCount();
	}

}
