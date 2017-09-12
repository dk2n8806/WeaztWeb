package com.core.service.notification;

import com.common.entity.notification.Notification;
import com.core.service.GenericService;

public interface NotificationService extends GenericService<Notification, Long> 
{
	
	Notification save(Notification notification);

}
