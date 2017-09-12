package com.core.service.process.subscription;

import com.common.entity.subscription.Subscription;
import com.common.exception.SubscriptionException;

public interface ICancelSubscriptionProcessService {

	/** {@link CancelSubscriptionProcessServiceImpl#cancel(Subscription)} */
	void cancel(Subscription subscription) throws SubscriptionException;
}
