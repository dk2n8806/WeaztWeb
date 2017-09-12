package com.core.service.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.util.date.DateInterval;
import com.core.dao.subscriber.BookmarkTemplateDTODao;

@Service
@Transactional
public class BookmarkTemplateDTOServiceImpl implements BookmarkTemplateDTOService{

	@Autowired private BookmarkTemplateDTODao templateDao;
	
	@Override
	public List<BookmarkProductTemplateDTO> getByAccount(
			Account account, DateInterval dateInterval, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return templateDao.getTemplates(account, null, true, dateInterval, pageable);
	}

	@Override
	public List<BookmarkProductTemplateDTO> getByProduct(
			Product product, DateInterval dateInterval, Pageable pageable) {
		if(product == null) return new ArrayList<>();
		return templateDao.getTemplates(null, product, true, dateInterval, pageable);
	}

}
