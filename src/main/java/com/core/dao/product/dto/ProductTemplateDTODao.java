package com.core.dao.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface ProductTemplateDTODao extends GenericRepository<ProductTemplateDTO, Long> {

	

	ProductTemplateDTO getTemplate(Product product);
	
	
	/** {@link ProductTemplateDTODaoImpl#getTemplates} */
	List<ProductTemplateDTO> getTemplates(Merchant merchant, List<Category> category
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	/** {@link ProductTemplateDTODaoImpl#getTemplates} */
	List<ProductTemplateDTO> getTemplates(Merchant merchant, GroupCategory category
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);
	
	
}
