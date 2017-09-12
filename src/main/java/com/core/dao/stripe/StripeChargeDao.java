package com.core.dao.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.core.dao.generic.GenericRepository;
import com.core.dao.stripe.impl.StripeChargeDaoImpl;

/***********************************************************************
 * 
 * @author dk
 *
 */
public interface StripeChargeDao extends GenericRepository<StripeCharge, Long>{
	
	/** {@link StripeChargeDaoImpl#getByToken(String)} */
	StripeCharge getByToken(String tokenId);
	
	/** {@link StripeChargeDaoImpl#getByStripeCustomer(StripeCustomer, Long)} */
	StripeCharge getByStripeCustomer(StripeCustomer customer, Long chargeId);
	
	/** {@link StripeChargeDaoImpl#getByAccount(Account, Long)} */
	StripeCharge getByAccount(Account account, Long chargeId);
	
	
	/** {@link StripeChargeDaoImpl#getListByStripeCustomer(StripeCustomer, Pageable)} */
	List<StripeCharge> getListByStripeCustomer(StripeCustomer stripeCustomer, Pageable pageable);
	
	/** {@link StripeChargeDaoImpl#getListByAccount(Account, Pageable)} */
	List<StripeCharge> getListByAccount(Account account, Pageable pageable);
	
	/** {@link StripeChargeDaoImpl#getCharges(Pageable)} */
	List<StripeCharge> getCharges(Pageable pageable);
	
	
	
	
	/** {@link StripeChargeDaoImpl#countByStripeCustomer(StripeCustomer)} */
	long countByStripeCustomer(StripeCustomer stripeCustomer);
	
	/** {@link StripeChargeDaoImpl#countByAccount(Account)} */
	long countByAccount(Account account);
	
	

}
