package com.core.service.subscriber;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.product.RecentViewProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.ProductStatus;
import com.core.dao.subscriber.RecentVisitedTemplateDTODao;

@Service
@Transactional(readOnly=true)
public class RecentVisitedTemplateDTOServiceImpl 
	implements RecentVisitedTemplateDTOService
{

	@Autowired private RecentVisitedTemplateDTODao templateDao;
	
	
	/** {@link RecentVisitedTemplateDTOService#getByAccount} */
	@Override
	public List<RecentViewProductTemplateDTO> getByAccount(Account account, ProductStatus status, Pageable pageable) {
		if(account == null) return new ArrayList<>();
		return templateDao.getProducts(account, status, pageable);
	}

}
