package com.core.service.notification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.notification.Notification;
import com.core.dao.notification.NotificationDao;
import com.core.service.notification.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

	@Autowired private NotificationDao notificationDao;
	
	@Override
	public Notification findById(Long id) {
		return notificationDao.findById(id);
	}

	@Override
	public Notification getReference(Long id) {
		return notificationDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return notificationDao.getRowCount();
	}
	
	

	@Override
	public Notification save(Notification notification) {
		if(notification != null) 
			notificationDao.persist(notification);
		return notification;
	}

}
