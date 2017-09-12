package com.core.dao.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.core.dao.account.impl.SimpleShippingDaoImpl;
import com.core.dao.generic.GenericRepository;

public interface SimpleShippingDao extends GenericRepository<SimpleShipping, Long>{


	/** {@link SimpleShippingDaoImpl#getPrimaryAddress(Account)}*/
	List<Address> getPrimaryAddress(Account account, Boolean isPrimary
			, Boolean isActive, Pageable pageable);
	
	
	/** {@link SimpleShippingDaoImpl#getByAccount(Account, Boolean, Boolean, Pageable)} */
	List<SimpleShipping> getByAccount(Account account, Boolean isPrimary
									, Boolean isActive, Pageable pageable);
	
	
	/** {@link SimpleShippingDaoImpl#countByAccount(Account, Boolean)} */
	long countByAccount(Account account, Boolean isActive);
	
}
