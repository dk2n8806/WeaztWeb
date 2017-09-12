package com.core.service.account.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.common.util.PageSearch;
import com.core.dao.account.SimpleShippingDao;
import com.core.service.account.SimpleShippingService;


/** 
 * 
 * @author dk2n_
 *
 */
@Service 
@Transactional
public class SimpleShippingServiceImpl implements SimpleShippingService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private SimpleShippingDao shippingDao;
	
	@Override
	public SimpleShipping findById(Long id) {
		return shippingDao.findById(id);
	}
	
	@Override
	public long getRowCount() {
		return shippingDao.getRowCount();
	}

	@Override
	public SimpleShipping getReference(Long id) {
		return shippingDao.getReference(id);
	}

	
		
	/** :::
	 * <p>Create & persist a new shipping_info by an account</p>
	 * 
	 * {@link SimpleShippingService#save(Account, AddressAdapter)}
	 */
	@Override
	public SimpleShipping save(Account account, Address adapter) {
		SimpleShipping shipping = SimpleShipping.create(account, adapter);
		if(shipping != null) {
			shippingDao.persist(shipping);
		}
		return shipping;
	}

		
	/** :::
	 * <p>lookup a primary shipping_info by the account</p>
	 * 
	 * {@link SimpleShippingService#getPrimaryByAccount(Account)}
	 */
	@Override
	public SimpleShipping getPrimaryByAccount(Account account) {
		List<SimpleShipping> shippings  = shippingDao.getByAccount(
											account, true, true, new PageSearch(0, 1));
		if(shippings.size() > 0) {
			return shippings.get(0);
		}
		return null;
	}
	
	
	@Override
	public Address getPrimaryAddress(Account account) {
		try {
			return shippingDao.getPrimaryAddress(account, true, true, new PageSearch(0,  1)).get(0);
		} catch(IndexOutOfBoundsException | NoResultException e) {
			return null;
		}
	}

	
	/** :::
	 * <p>Lookup a shipping_info by an account on a id</p>
	 * 
	 * {@link SimpleShippingService#getByAccount(Long, Account)}
	 * ::: */
	@Override
	public SimpleShipping getByAccount(Long shippingId, Account account) {
		SimpleShipping shipping = shippingDao.findById(shippingId);
		if(shipping != null) {
			if(shipping.getAccount().getId().equals(account.getId())) {
				return shipping;
			}
		}
		return null;
	}

	
	
		
	/** :::
	 * <p>Set a shipping_info as primary</p>
	 * 
	 * {@link SimpleShippingService#setPrimary(SimpleShipping)}
	 * ::: */
	@Override
	public void setPrimary(SimpleShipping shipping) {
		if(shipping != null) {
			// Validate simple_shipping before update to 'PRIMARY'
			if(shipping.getAccount() != null && shipping.isActive()) {
				SimpleShipping primary = getPrimaryByAccount(shipping.getAccount());
				if(primary != null) {	
					// Normalize previous primary shipping_info
					primary.setPrimary(false);
					shippingDao.update(primary);
				}
				
				// Primarize the shipping_info
				shipping.setPrimary(true);
				shippingDao.update(shipping);
			} else {
				logger.error("[ERROR] the simple_shipping must be active");
			}
		}
	}

	
		
	/** :::
	 * <p>Retrieve a list of shipping_info on an account</p>
	 * 
	 * {@link SimpleShippingService#getByAccount(Account, Boolean, Boolean, Pageable)}
	 * ::: */
	@Override
	public List<SimpleShipping> getByAccount(Account account
			, Boolean isPrimary, Boolean isActive, Pageable pageable) {
		if(account == null) {
			return new ArrayList<>();
		}
		return shippingDao.getByAccount(account, isPrimary, isActive, pageable);
	}

	
	
	
		
	/** :::
	 * <p>Count total shipping_info by an account</p>
	 * 
	 * {@link SimpleShippingService#countByAccount(Account, Boolean)}
	 * ::: */
	@Override
	public long countByAccount(Account account, Boolean isActive) {
		if(account == null) {
			return 0;
		}
		try {
			return shippingDao.countByAccount(account, isActive);
		} catch(NoResultException e) {
			return 0;
		}
	}

	
	
	/** :::
	 * <p>Deactive a shipping info</p>
	 * 
	 * {@link SimpleShippingService#deactive(SimpleShipping)}
	 * ::: */
	@Override
	public void deactive(SimpleShipping shipping) {
		if(shipping != null) {
			shipping.setActive(false);
			shipping.setPrimary(false);
			shippingDao.update(shipping);
		}
	}

}
