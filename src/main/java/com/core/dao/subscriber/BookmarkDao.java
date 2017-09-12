package com.core.dao.subscriber;

import com.common.entity.account.Account;
import com.common.entity.subscriber.Bookmark;
import com.core.dao.generic.GenericRepository;

public interface BookmarkDao extends GenericRepository<Bookmark, Long>{

	/** {@link BookmarkDaoImpl#getByAccount(Long, Account)} */
	Bookmark getByAccount(Long productId, Account account);
}
