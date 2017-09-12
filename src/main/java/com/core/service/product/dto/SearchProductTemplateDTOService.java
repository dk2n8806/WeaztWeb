package com.core.service.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.ProductTemplateDTO;
import com.common.type.ProductStatus;

public interface SearchProductTemplateDTOService {

	long countByTitle(String title, ProductStatus status);
	
	/** {@link SearchProductTemplateDTOServiceImpl#getByTitle(String, ProductStatus, Pageable)} */
	List<ProductTemplateDTO> getByTitle(String title, ProductStatus status, Pageable pageable);
}
