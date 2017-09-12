package com.core.service.merchant.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.account.Account;
import com.common.entity.address.Address;
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.TaxRate;
import com.common.entity.support.embeded.Phone;
import com.common.type.MerchantStatus;
import com.common.util.date.DateInterval;
import com.core.dao.merchant.MerchantDao;
import com.core.service.merchant.MerchantService;


/**
 * 
 * @author dk2n_
 *
 */
@Service
@Transactional
public class MerchantServiceImpl 
	implements MerchantService
{
	
	@Autowired private MerchantDao merchantDao;

	
	/** :::
	 * <p>Find the merchant entity by the merchant_id</p>
	 * {@link MerchantService#findById(Long)}
	 * ::: */
	@Override
	public Merchant findById(Long id) {
		return merchantDao.findById(id);
	}

	
	

	/** :::
	 * <p>Retrieve a merchant enitty proxy by a merchant_id</p>
	 * {@link MerchantService#getReference(Long)}
	 * ::: */
	@Override
	public Merchant getReference(Long id) {
		return merchantDao.getReference(id);
	}

	
	

	/** :::
	 * <p>Count total entities of the merchant table</p>
	 * {@link MerchantServiceImpl#getRowCount()}
	 * ::: */
	@Override
	public long getRowCount() {
		return merchantDao.getRowCount();
	}

	
	

	/** :::
	 * <p>Create & save a new merchant entity</p>
	 * {@link MerchantService#save(Account, String, String, Phone, Address)}
	 * ::: */
	@Override
	public Merchant save(Account account, String companyName, String webstie
										, Phone phone, Address address) 
	{
		Merchant merchant = Merchant.createEntityInstance(
															account, companyName, webstie
															, MerchantProfile.createEntityInstance(phone, address));
	
		if(merchant != null) {
			merchantDao.persist(merchant);
		}
		return merchant;
	}

	
	

	/** :::
	 * 
	 * ::: */
	@Override
	public Merchant getByAccount(Account account) {
		try {
			return merchantDao.getByAccount(account);
		} catch(NoResultException e)  {
			return null;
		}
	}

	
	

	/** :::
	 * <p>Retrieve a list of merchant entities</p>
	 * {@link MerchantService#getMerchants(MerchantStatus, DateInterval, Pageable)}
	 * ::: */
	@Override
	public List<Merchant> getMerchants(MerchantStatus status
								, DateInterval dateInterval, Pageable pageable) 
	{
		return merchantDao.getMerchants(status, dateInterval, pageable);
	}

	
	

	/** :::
	 * <p>Count total merchant entities</p>
	 * {@link MerchantService#countMerchants(MerchantStatus, DateInterval)}
	 * ::: */
	@Override
	public long countMerchants(MerchantStatus status, DateInterval dateInterval) {
		try {
			return merchantDao.countMerchants(status, dateInterval);
		} catch(NoResultException e) {
			return 0;
		}
	}





	/** :::
	 * <p>Lookup merchant_profile</p>
	 * {@link MerchantService#getProfile(Merchant)}
	 * ::: */
	@Override
	public MerchantProfile getProfile(Merchant merchant) {
		try {
			return merchantDao.getProfile(merchant);
		} catch(NoResultException e) {
			return null;
		}
	}





	/** :::
	 * <p>Lookup commission_rate of the merchant</p>
	 * {@link MerchantService#getCommissionRate(Merchant)}
	 * ::: */
	@Override
	public CommissionRate getCommissionRate(Merchant merchant) {
		try {
			return merchantDao.getCommissionRate(merchant);
		} catch(NoResultException e) {
			return null;
		}
	}





	/** :::
	 * <p>Retrieve an account of the merchant</p>
	 * {@link MerchantService#getAccount(Merchant)}
	 * ::: */
	@Override
	public Account getAccount(Merchant merchant) {
		try {
			return merchantDao.getAccount(merchant);
		} catch(NoResultException e) {
			return null;
		}
	}





	/** :::
	 * <p>Update a merchant status</p>
	 * {@link MerchantService#updateStatus(Long, MerchantStatus)}
	 * ::: */
	@Override
	public void updateStatus(Long merchantId, MerchantStatus status) {
		merchantDao.updateStatus(merchantId, status);
	}




	
	/** :::
	 * <p>Lookup tax_rate entity of the merchant</p>
	 * {@link MerchantService#getTaxRate(Merchant)}
	 * ::: */
	@Override
	public TaxRate getTaxRate(Merchant merchant) {
		try {
			return merchantDao.getTaxRate(merchant);
		} catch(NoResultException e) {
			return null;
		}
	}

}
