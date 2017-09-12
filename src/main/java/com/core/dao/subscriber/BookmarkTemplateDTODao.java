package com.core.dao.subscriber;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface BookmarkTemplateDTODao extends GenericRepository<BookmarkProductTemplateDTO, Long> {

	/** {@link BookmarkTemplateDTODAOImpl#getTemplates} */
	List<BookmarkProductTemplateDTO> getTemplates(
			Account account, Product product, Boolean isActive, DateInterval dateInterval, Pageable pageable);
}
