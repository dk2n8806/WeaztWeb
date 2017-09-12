package com.core.service.account;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.core.service.GenericService;
import com.core.service.account.impl.SimpleShippingServiceImpl;


public interface SimpleShippingService extends GenericService<SimpleShipping, Long>{

	/** :::
	 * Create & save a new shipping_info by an account 
	 * {@link SimpleShippingServiceImpl#save(Account, Address)} 
	 * ::: */
	SimpleShipping save(Account account, Address address);

	
	/** 
	 * Lookup the primary shipping_info by the account
	 * {@link SimpleShippingServiceImpl#getPrimaryByAccount(Account)} 
	 * */
	SimpleShipping getPrimaryByAccount(Account account);
	
	
	/** {@link SimpleShippingServiceImpl#getPrimaryAddress(Account)} */
	Address getPrimaryAddress(Account account);
	
	
	/** 
	 * Lookup an shipping_info by an account on a given id 
	 * {@link SimpleShippingServiceImpl#getByAccount(Long, Account)} 
	 * */
	SimpleShipping getByAccount(Long shippingId, Account account);
	
	
	
	/** {@link SimpleShippingServiceImpl#deactive(SimpleShipping)} */
	void deactive(SimpleShipping shipping);
	
	
	
	
	/** 
	 * Set a shipping_info as primary
	 * {@link SimpleShippingServiceImpl#setPrimary(SimpleShipping)} 
	 * */
	void setPrimary(SimpleShipping shipping);
	
	
	
	
	/**
	 * Retrieve a list of shipping_info by an account
	 * {@link SimpleShippingServiceImpl#getByAccount(Account, Boolean, Boolean, Pageable)} 
	 * */
	List<SimpleShipping> getByAccount(Account account, Boolean isPrimary, Boolean isActive, Pageable pageable);
	
	
	
	
	/** 
	 * Count total shipping_info by an account 
	 * {@link SimpleShippingServiceImpl#countByAccount(Account, Boolean)}
	 * */
	long countByAccount(Account account, Boolean isActive);
}
