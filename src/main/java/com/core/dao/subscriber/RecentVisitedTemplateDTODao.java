package com.core.dao.subscriber;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.RecentViewProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.ProductStatus;
import com.core.dao.generic.GenericRepository;

public interface RecentVisitedTemplateDTODao 
	extends GenericRepository<RecentViewProductTemplateDTO, Long>
{

	/** {@link RecentVisitedTemplateDTODaoImpl#getProducts} */
	List<RecentViewProductTemplateDTO> getProducts(Account account, ProductStatus status, Pageable pageable);
}
