package com.core.service.product.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.product.DeletedProductTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.util.date.DateInterval;

public interface DeletedProductTemplateDTOService {

	List<DeletedProductTemplateDTO> getByMerchant(
			Merchant merchant, DateInterval dateInterval, Pageable pageable);
}
