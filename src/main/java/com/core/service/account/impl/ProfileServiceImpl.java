package com.core.service.account.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.core.dao.account.ProfileDao;
import com.core.service.account.ProfileService;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService
{

	@Autowired private ProfileDao profileDao;
	
	
	@Override
	public Profile findById(Long id) {
		return profileDao.findById(id);
	}

	@Override
	public Profile getReference(Long id) {
		return profileDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return profileDao.getRowCount();
	}
	
	

	@Override
	public Profile getByAccount(Account customer) {
		try {
			return profileDao.getByAccount(customer);
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Profile> getProfiles(Pageable pageable) {
		return profileDao.getProfiles(pageable);
	}

	@Override
	public void update(Profile profile) {		
		if(profile != null)
			profileDao.update(profile);
	}

}
