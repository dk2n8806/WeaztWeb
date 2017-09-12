package com.core.service.stripe.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.ChargeAdapterAttribute;
import com.common.adapter.stripe.ChargeAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.ChargeAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;


/************************************************************
 * 
 * @author dk
 *
 */
@Service("stripeChargeAdapterServiceImpl")
public class ChargeAdapterServiceImpl implements ChargeAdapterService {

	

	@Override
	public ChargeAdapter create(ChargeAdapterAttribute chargeAdapter)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Charge charge = Charge.create(chargeAdapter.getChargeMetadata()
									, StripeRequestManage.getOptions());
		return ChargeAdapter.create(charge);
	}

	@Override
	public ChargeAdapter update(String tokenId,
			ChargeAdapterAttribute chargeAdapter) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		Charge charge = Charge.retrieve(tokenId
										, StripeRequestManage.getOptions());
		Charge updated = charge.update(chargeAdapter.getChargeMetadata()
										, StripeRequestManage.getOptions());
		return ChargeAdapter.create(updated);
	}

	
	
	
/******************************************************************
 * 
 * {@link ChargeAdapterService#lookup(String)}
 */
	@Override
	public ChargeAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Charge charge = Charge.retrieve(tokenId
				, StripeRequestManage.getOptions());
		return ChargeAdapter.create(charge);
	}


	
	
	


}
