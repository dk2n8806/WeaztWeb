package com.core.dao;

import java.util.List;

import com.common.entity.AbstractPersistenceToken;
import com.core.dao.generic.GenericRepository;

public interface AbstractTokenDao extends GenericRepository<AbstractPersistenceToken, Long>{

	/** {@link AbstractTokenDaoImpl#getById(Long)} */
	AbstractPersistenceToken getById(Long tokenId);
	
	/** {@link AbstractTokenDaoImpl#updateStatus(List, boolean)} */
	void updateStatus(List<? extends AbstractPersistenceToken> tokens, boolean isUseable);
}
