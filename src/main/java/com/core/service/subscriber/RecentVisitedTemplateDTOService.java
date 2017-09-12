package com.core.service.subscriber;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.RecentViewProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.ProductStatus;

public interface RecentVisitedTemplateDTOService {

	/** {@link RecentVisitedTemplateDTOServiceImpl#getByAccount} */
	List<RecentViewProductTemplateDTO> getByAccount(Account account, ProductStatus status, Pageable pageable);
	
}
