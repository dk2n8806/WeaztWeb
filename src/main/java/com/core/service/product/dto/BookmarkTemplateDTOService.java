package com.core.service.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.util.date.DateInterval;

public interface BookmarkTemplateDTOService {

	/** {@link BookmarkTemplateDTOServiceImpl#getByAccount(Account, DateInterval, Pageable)} */
	List<BookmarkProductTemplateDTO> getByAccount(Account account, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link BookmarkTemplateDTOServiceImpl#getByProduct(Product, DateInterval, Pageable)} */
	List<BookmarkProductTemplateDTO> getByProduct(Product product, DateInterval dateInterval, Pageable pageable);
}
