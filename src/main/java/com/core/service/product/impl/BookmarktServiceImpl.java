package com.core.service.product.impl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.subscriber.Bookmark;
import com.core.dao.subscriber.BookmarkDao;
import com.core.service.product.BookmarkService;

@Service
@Transactional
public class BookmarktServiceImpl implements BookmarkService{

	@Autowired private BookmarkDao wishlistDao;

	@Override
	public Bookmark save(Account account, Product product) {
		Bookmark list = getByAccount(product.getId(), account);
		if(list == null) {
			list = Bookmark.create(account, product);
			if(list != null)  {
				wishlistDao.persist(list);
			}
		} else if(!list.isActive()) {
			list.setActive(true);
			wishlistDao.update(list);
		}
		return list;
	}

	@Override
	public Bookmark getByAccount(Long productId, Account account) {
		try {
			return wishlistDao.getByAccount(productId, account);
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void remove(Bookmark wishList) {
		if(wishList.isActive()) {
			wishList.setActive(false);
			wishlistDao.update(wishList);
		}
	}

}
