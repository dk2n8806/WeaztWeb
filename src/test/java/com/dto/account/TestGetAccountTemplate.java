package com.dto.account;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.dto.AccountTemplateDTO;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.service.account.dto.AccountTemplateDTOService;

public class TestGetAccountTemplate extends BaseTest {

	@Autowired private AccountTemplateDTOService templateDTOService;
	private Role role;
	private AccountStatus status;
	private DateInterval dateInterval;
	private Pageable pageable;
	
	
	@Test
	public void test() {
		List<AccountTemplateDTO> templateDTOs = 
				templateDTOService.getTemplates(role, status, dateInterval, pageable);

		System.out.println(templateDTOs.size());
	}
}
