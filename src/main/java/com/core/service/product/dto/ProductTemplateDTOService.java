package com.core.service.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;

public interface ProductTemplateDTOService {


	ProductTemplateDTO getTemplate(Product product);

	/** {@link ProductTemplateDTOServiceImpl#getTemplates} */
	List<ProductTemplateDTO> getTemplates(List<Category> categories
			, ProductStatus status, DateInterval dateInterval, Pageable pageable);

	
	/** {@link ProductTemplateDTOServiceImpl#getTemplates(Category, ProductStatus, DateInterval, Pageable)} */
	List<ProductTemplateDTO> getTemplates(Category category
			, ProductStatus status, DateInterval dateInterval, Pageable pageable);

	
	/** {@link ProductTemplateDTOServiceImpl#getByMerchant} */
	List<ProductTemplateDTO> getByMerchant(Merchant merchant, List<Category> categories
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);


	/** {@link ProductTemplateDTOServiceImpl#getByMerchant(Merchant, Category, ProductStatus, DateInterval, Pageable)} */
	List<ProductTemplateDTO> getByMerchant(Merchant merchant, Category category
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	
	/** {@link ProductTemplateDTOServiceImpl#getTemplates} */
	List<ProductTemplateDTO> getTemplates(GroupCategory category
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);
	
	/** {@link ProductTemplateDTOServiceImpl#getByMerchant} */
	List<ProductTemplateDTO> getByMerchant(Merchant merchant, GroupCategory category
									, ProductStatus status, DateInterval dateInterval, Pageable pageable);
	
	
	
	
	 
}
