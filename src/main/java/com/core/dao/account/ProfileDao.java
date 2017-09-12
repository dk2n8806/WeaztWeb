package com.core.dao.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.core.dao.generic.GenericRepository;

public interface ProfileDao 
extends GenericRepository<Profile, Long>{

	/** {@link ProfileDaoImpl#getByAccount(Account)} */
	Profile getByAccount(Account customer);
	
	
	/** {@link ProfileDaoImpl#getProfiles(Pageable)} */
	List<Profile> getProfiles(Pageable pageable);
	
}
