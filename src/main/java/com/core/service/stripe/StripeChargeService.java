package com.core.service.stripe;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.adapter.stripe.ChargeAdapter;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.core.service.GenericService;
import com.core.service.stripe.impl.StripeChargeServiceImpl;


/****************************************************************************
 * 
 * @author dk
 *
 */
public interface StripeChargeService 
extends GenericService<StripeCharge, Long> {

	/** {@link StripeChargeServiceImpl#create(StripeCustomer, ChargeAdapter)} */
	StripeCharge create(StripeCustomer stripeCustomer, ChargeAdapter chargeAdapter);
	
	
	/** {@link StripeChargeServiceImpl#getByToken(String)} */
	StripeCharge getByToken(String tokenId);
	
	/** {@link StripeChargeServiceImpl#getByStripeCustomer(StripeCustomer, Long)} */
	StripeCharge getByStripeCustomer(StripeCustomer customer, Long chargeId);
	
	/** {@link StripeChargeServiceImpl#getByAccount(Account, Long)} */
	StripeCharge getByAccount(Account account, Long chargeId);
	
	
	
	
	
	/** {@link StripeChargeServiceImpl#getListByStripeCustomer(StripeCustomer, Pageable)} */
	List<StripeCharge> getListByStripeCustomer(StripeCustomer stripeCustomer, Pageable pageable);
	
	/** {@link StripeChargeServiceImpl#getListByAccount(Account, Pageable)} */
	List<StripeCharge> getListByAccount(Account account, Pageable pageable);
	
	/** {@link StripeChargeServiceImpl#getCharges(Pageable)} */
	List<StripeCharge> getCharges(Pageable pageable);
	
	
	
	
	/** {@link StripeChargeServiceImpl#countByStripeCustomer(StripeCustomer)} */
	long countByStripeCustomer(StripeCustomer stripeCustomer);
	
	/** {@link StripeChargeServiceImpl#countByAccount(Account)} */
	long countByAccount(Account account);
	
	
}
