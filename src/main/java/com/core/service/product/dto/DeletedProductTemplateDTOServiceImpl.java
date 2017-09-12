package com.core.service.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.product.DeletedProductTemplateDTO;
import com.common.entity.merchant.Merchant;
import com.common.util.date.DateInterval;
import com.core.dao.product.dto.DeletedProductTemplateDTODao;

@Service
@Transactional
public class DeletedProductTemplateDTOServiceImpl implements DeletedProductTemplateDTOService{

	@Autowired private DeletedProductTemplateDTODao templateDTODao;

	@Override
	public List<DeletedProductTemplateDTO> getByMerchant(
			Merchant merchant, DateInterval dateInterval, Pageable pageable) {
		if(merchant == null) return new ArrayList<>();
		return templateDTODao.getTemplates(merchant, dateInterval, pageable);
	}
}
