package com.core.service.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.core.service.GenericService;
import com.core.service.account.impl.ProfileServiceImpl;

public interface ProfileService extends GenericService<Profile, Long>{


	/** {@link ProfileServiceImpl#update(Profile)} */
	@Deprecated void update(Profile profile);

	/** {@link ProfileServiceImpl#getByAccount(Account)} */
	Profile getByAccount(Account customer);

	/** {@link ProfileServiceImpl#getProfiles(Pageable)} */
	List<Profile> getProfiles(Pageable pageable);
	
}
