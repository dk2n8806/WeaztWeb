package com.core.dao.notification.impl;

import org.springframework.stereotype.Repository;

import com.common.entity.notification.Notification;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.notification.NotificationDao;

@Repository
public class NotificationDaoImpl 
	extends GenericJpaRepository<Notification, Long>
implements NotificationDao 
{
	

}
