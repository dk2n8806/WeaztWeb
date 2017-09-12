package com.core.dao.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.DeletedProductTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;

public interface DeletedProductTemplateDTODao extends GenericRepository<DeletedProductTemplateDTO, Long>{

	List<DeletedProductTemplateDTO> getTemplates(
					Merchant merchant, DateInterval dateInterval, Pageable pageable);
}
