package com.core.dao.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.ProductTemplateDTO;
import com.common.type.ProductStatus;

public interface SearchProductTemplateDTODao {

	/** {@link SearchTemplateDaoImpl#countByTitle(String[], ProductStatus)} */
	long countByTitle(String title[], ProductStatus status);

	/** {@link SearchTemplateDaoImpl#getByTitle(String[], ProductStatus, Pageable)} */
	List<ProductTemplateDTO> getByTitle(String title[], ProductStatus status, Pageable pageable);
	
}
