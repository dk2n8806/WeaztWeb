package com.core.service.product.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.type.ProductStatus;
import com.common.util.date.DateInterval;
import com.core.dao.product.dto.ProductTemplateDTODao;

@Service
@Transactional(readOnly=true)
public class ProductTemplateDTOServiceImpl  implements ProductTemplateDTOService {

	@Autowired private ProductTemplateDTODao templateDao;

	/** ::: <p>Retrieve a list of product templates by a merchant</p>
	 * {@link ProductTemplateDTOService#getByMerchant}
	 */
	@Override
	public List<ProductTemplateDTO> getByMerchant(Merchant merchant
			, List<Category> categories, ProductStatus status, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null)return new ArrayList<>();
		return templateDao.getTemplates(merchant, categories, status, dateInterval, pageable);
	}




	@Override
	public List<ProductTemplateDTO> getByMerchant(Merchant merchant
			, Category category, ProductStatus status, DateInterval dateInterval, Pageable pageable) {
		return this.getByMerchant(merchant, Arrays.asList(category), status, dateInterval, pageable);
	}
	
	
	/** ::: <p>Retrieve a list of products templates</p>
	 * {@link ProductTemplateDTOService#getTemplates}
	 * :::  */
	@Override
	public List<ProductTemplateDTO> getTemplates(
			List<Category> categories, ProductStatus status, DateInterval dateInterval, Pageable pageable) {
		return templateDao.getTemplates(null, categories, status, dateInterval, pageable);
	}



	/** ::: <p>Retrieve a list of products on a category</p>
	 * {@link ProductTemplateDTOService#getTemplates(Category, ProductStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<ProductTemplateDTO> getTemplates(
			Category category, ProductStatus status, DateInterval dateInterval, Pageable pageable) {
		return getTemplates(Arrays.asList(category), status, dateInterval, pageable);
	}
	



	@Override
	public List<ProductTemplateDTO> getTemplates(GroupCategory category, ProductStatus status,
			DateInterval dateInterval, Pageable pageable) {
		return templateDao.getTemplates(null, category, status, dateInterval, pageable);
	}
	
	@Override
	public List<ProductTemplateDTO> getByMerchant(Merchant merchant, GroupCategory category, ProductStatus status,
			DateInterval dateInterval, Pageable pageable) {
		if(merchant == null)return new ArrayList<>();
		return templateDao.getTemplates(merchant, category, status, dateInterval, pageable);
	}




	/** :::
	 * {@link ProductTemplateDTOService#getTemplate(Product)}
	 */
	@Override
	public ProductTemplateDTO getTemplate(Product product) {
		try {
			return templateDao.getTemplate(product);
		} catch(NoResultException e) {
			return null;
		}
	}




}
