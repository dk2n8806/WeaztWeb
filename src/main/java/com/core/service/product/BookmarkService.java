package com.core.service.product;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.Bookmark;
import com.core.service.product.impl.BookmarktServiceImpl;

public interface BookmarkService {

	/** {@link BookmarktServiceImpl#save(Account, Product)} */
	Bookmark save(Account account, Product product);
	
	/** {@link BookmarktServiceImpl#remove(Bookmark)} */
	void remove(Bookmark wishList);
	
	/** {@link BookmarktServiceImpl#getByAccount(Long, Account)} */
	Bookmark getByAccount(Long productId, Account account);
}
