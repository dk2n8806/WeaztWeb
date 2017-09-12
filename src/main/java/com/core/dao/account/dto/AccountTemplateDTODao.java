package com.core.dao.account.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.dto.AccountTemplateDTO;
import com.common.entity.account.Account;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericRepository;


/**
 * <p>DAO to access account table</p>
 * 
 * @author dk2n_
 *
 */
public interface AccountTemplateDTODao extends GenericRepository<AccountTemplateDTO, Long>{

	/** {@link AccountTemplateDTODaoImpl#getTemplates(Role, AccountStatus, DateInterval, Pageable)} */
	List<AccountTemplateDTO> getTemplates(Role role
							, AccountStatus status,  DateInterval dateInterval, Pageable pageable);
	
	/** {@link AccountTemplateDTODaoImpl#getTemplate(Account)} */
	AccountTemplateDTO getTemplate(Account account);
}
