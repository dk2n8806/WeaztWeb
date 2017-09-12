package com.core.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.dao.account.AvatarDao;
import com.core.service.account.AvatarService;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

	@Autowired private AvatarDao avatarDao;
	
	@Override
	public void deactive(Long avatarId) {
		avatarDao.setActive(avatarId, false);
	}

}
