package com.core.service.product.dto;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.product.ProductTemplateDTO;
import com.common.tool.SearchTool;
import com.common.type.ProductStatus;
import com.core.dao.product.dto.SearchProductTemplateDTODao;

@Service
@Transactional
public class SearchProductTemplateDTOServiceImpl  implements SearchProductTemplateDTOService{

	@Autowired private SearchProductTemplateDTODao searchDao;
	
	@Override
	public long countByTitle(String title, ProductStatus status) {
		try {
			return searchDao.countByTitle(
					new SearchTool(title).getSearchFormat(), status);
		} catch(NoResultException e) {
			return 0;
		}
	}

	@Override
	public List<ProductTemplateDTO> getByTitle(String title, ProductStatus status, Pageable pageable) {
		return searchDao.getByTitle(
						new SearchTool(title).getSearchFormat(), status, pageable);
	}

}
