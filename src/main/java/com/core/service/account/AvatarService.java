package com.core.service.account;

import com.core.service.account.impl.AvatarServiceImpl;

public interface AvatarService {

	/** {@link AvatarServiceImpl#deactive(Long)} */
	void deactive(Long avatarId);
}
