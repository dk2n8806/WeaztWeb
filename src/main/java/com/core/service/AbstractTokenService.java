package com.core.service;

import java.util.List;

import com.common.entity.AbstractPersistenceToken;

public interface AbstractTokenService {
	
	/** {@link AbstractTokenServiceImpl#getById(Long)} */
	AbstractPersistenceToken getById(Long tokenId);
	
	/** {@link AbstractTokenServiceImpl#updateStatus(AbstractPersistenceToken, boolean)} */
	void updateStatus(AbstractPersistenceToken token, boolean isUseable);
	
	/** {@link AbstractTokenServiceImpl#updateStatus(List, boolean)} */
	void updateStatus(List<? extends AbstractPersistenceToken> tokens, boolean isUseable);
}
